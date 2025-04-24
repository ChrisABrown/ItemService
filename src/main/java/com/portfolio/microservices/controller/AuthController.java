package com.portfolio.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.AuthService;
import com.portfolio.microservices.suprimeapi.api.AuthApi;
import com.portfolio.microservices.suprimeapi.model.LoginRequest;
import com.portfolio.microservices.suprimeapi.model.LoginResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(LoginRequest request) {
        LoginResponse response = authService.login(request.getEmail(),
                request.getPassword());
        return ResponseEntity.ok(response);
    }
    
    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logoutUser'");
    }
}
