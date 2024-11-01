package com.example.spring_first_project.controller;

import com.example.spring_first_project.model.User;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller()
public class UserController {
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String trangChiTiet(@ModelAttribute("userName") String userName, Model model) {
        List<UserDemo> list = userService.getAllUser();
        list.forEach(s -> logger.info(s.toString()));
        model.addAttribute("userDemo", list);
        model.addAttribute("userName", userName);
        return "user";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") UserDemo user, RedirectAttributes redirectAttributes) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        redirectAttributes.addFlashAttribute("userName", fullName);
        userService.saveOrUpdate(user);
        return "redirect:/user";
    }
}