package com.portfolio.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.suprimeapi.model.LoginResponse;
import com.portfolio.microservices.suprimeapi.model.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
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
