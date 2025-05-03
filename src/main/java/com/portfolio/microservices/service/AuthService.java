package com.portfolio.microservices.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.suprimeapi.model.LoginResponse;
import com.portfolio.microservices.suprimeapi.model.User;

@Component
public class AuthService {

    private final UserRepository repo;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;
    private final String USER_NOT_FOUND = "User not found";
    private final String INVALID_CREDENTIALS = "Invalid credentials";
    private final String EMAIL_REQUIRED = "Email is required";
    private final String EMAIL_LENGTH = "Email must be at least 5 characters long";
    private final String EMAIL_NOT_VALID = "Email is not valid";
    private final String EMAIL_NO_SPACES = "Email must not contain spaces";
    private final String EMAIL_TOO_LONG = "Email must be at most 50 characters long";
    private final String EMAIL_LENGTH_RANGE = "Email must be between 5 and 50 characters long";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String PASSWORD_REQUIRED = "Password is required";
    private final String PASSWORD_LENGTH = "Password must be at least 8 characters long";
    private final String PASSWORD_LENGTH_RANGE = "Password must be between 8 and 50 characters long";
    private final String PASSWORD_NOT_VALID = "Password must contain at least one uppercase letter, one lowercase letter, and one number";
    private final String PASSWORD_NO_SPACES = "Password must not contain spaces";
    private final String PASSWORD_TOO_LONG = "Password must be at most 50 characters long";
    private final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private final String LOGIN_FAILURE = "Login failed: ";

    public AuthService(UserRepository userRepository, JwtService jwtService1,
            TokenBlacklistService tokenBlacklistService1) {
        this.repo = userRepository;
        this.jwtService = jwtService1;
        this.tokenBlacklistService = tokenBlacklistService1;
    }

    public LoginResponse login(String email, String password) {
        User user = repo.findByEmail(email);

        try {
            if (user == null) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            if (email == null || email.isEmpty()) {
                throw new RuntimeException(EMAIL_REQUIRED);
            }
            if (password == null || password.isEmpty()) {
                throw new RuntimeException(PASSWORD_REQUIRED);
            }
            if (email.length() < 5) {
                throw new RuntimeException(EMAIL_LENGTH);
            }
            if (password.length() < 8) {
                throw new RuntimeException(PASSWORD_LENGTH);
            }
            if (!email.matches(EMAIL_REGEX)) {
                throw new RuntimeException(EMAIL_NOT_VALID);
            }
            if (!password.matches(PASSWORD_REGEX)) {
                throw new RuntimeException(PASSWORD_NOT_VALID);
            }
            if (password.contains(" ")) {
                throw new RuntimeException(PASSWORD_NO_SPACES);
            }
            if (email.contains(" ")) {
                throw new RuntimeException(EMAIL_NO_SPACES);
            }
            if (email.length() > 50) {
                throw new RuntimeException(EMAIL_TOO_LONG);
            }
            if (password.length() > 50) {
                throw new RuntimeException(PASSWORD_TOO_LONG);
            }
            if (email.length() < 5 || email.length() > 50) {
                throw new RuntimeException(EMAIL_LENGTH_RANGE);
            }
            if (password.length() < 8 || password.length() > 50) {
                throw new RuntimeException(PASSWORD_LENGTH_RANGE);
            }
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new RuntimeException(INVALID_CREDENTIALS);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(LOGIN_FAILURE + e.getMessage());
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse()
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .userId(user.getUserId());
    }

    public void logout(String token) {
        tokenBlacklistService.blacklist(token);
    }
}
