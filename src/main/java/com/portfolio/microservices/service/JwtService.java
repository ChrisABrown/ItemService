package com.portfolio.microservices.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.portfolio.microservices.suprimeapi.model.RoleEnum;
import com.portfolio.microservices.suprimeapi.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtService {

    // Generate a secure random key
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); // HS256 algorithm

    // Token expiration (e.g., 10 hours)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate JWT token
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().toString())
                .claim("userId", user.getUserId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            // Check if the token is expired
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseUnsecuredClaims(token);
            // Verify token using the SECRET_KEY

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Token is invalid or expired
            return false;
        }
    }

    // Extract email from JWT token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract user ID from JWT token
    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }

    // Extract role from JWT token
    public RoleEnum extractRole(String token) {
    return extractClaim(token, claims -> RoleEnum.fromValue(claims.get("role",
    String.class)));
    }

    // Extract a specific claim from the token
    private <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY) // Verify token using the SECRET_KEY
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
