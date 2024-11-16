package com.example.spring_first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class LoginController {

    public LoginController() {}

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}