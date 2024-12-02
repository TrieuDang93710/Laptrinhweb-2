package com.example.spring_first_project.controller;

import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller()
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDemo());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute UserDemo user, RedirectAttributes redirectAttributes) {

        return "redirect:/user";
    }

    @GetMapping("/loginUser")
    public String loginForm(Model model) {
        model.addAttribute("user", new UserDemo());
        return "login";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
//        userService.deleteUserById(id);
        return "redirect:/user";
    }
}