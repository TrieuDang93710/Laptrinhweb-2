package com.example.spring_first_project.controller;

import com.example.spring_first_project.dto.LoginResponse;
import com.example.spring_first_project.dto.UserLoginDto;
import com.example.spring_first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            LoginResponse token = userService.login(userLoginDto);
            return ResponseEntity.ok(token); // Trả về token
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}