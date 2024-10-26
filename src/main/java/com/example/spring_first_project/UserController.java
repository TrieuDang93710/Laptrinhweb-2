package com.example.spring_first_project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
public class UserController {
    @GetMapping("/user")
    public String trangChiTiet(@ModelAttribute("userName") String userName, Model model) {
        model.addAttribute("userName", userName);
        return "user";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        redirectAttributes.addFlashAttribute("userName", fullName);
        return "redirect:/user";
    }
}