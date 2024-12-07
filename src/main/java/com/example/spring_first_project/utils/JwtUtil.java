package com.example.spring_first_project.utils;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public interface JwtUtil{

    String generateToken(String email);
    String createToken(Map<String, Object> claims, String subject);
    boolean validateToken(String token, String email);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    String extractEmail(String token);
    Claims extractAllClaims(String token);
}
