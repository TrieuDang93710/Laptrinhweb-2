package com.example.spring_first_project.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtilImpl implements JwtUtil {

    private String SECRET_KEY = "secret_key_token";
    private long EXPIRATION_TIME = 1000 * 60 * 60;

    @Override
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    @Override
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
//        return (extractedEmail.equals(email) && !isTokenExpired(token));
        return true;
    }

    @Override
    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
        return true;
    }

    @Override
    public Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    }

    @Override
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
