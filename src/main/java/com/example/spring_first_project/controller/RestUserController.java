package com.example.spring_first_project.controller;

import com.example.spring_first_project.dto.UserRegistrationApiDto;
import com.example.spring_first_project.dto.UserUpdateApiDto;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.repository.UserRepository;
import com.example.spring_first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDemo>> getUsers() {
        List<UserDemo> users = userService.findAll();
        System.out.println(users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/string/{email}")
    public ResponseEntity<UserDemo> getUser(@PathVariable String email) {
        UserDemo user = userService.findByUsername(email);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/int/{id}")
    public ResponseEntity<UserDemo> findUserById(@PathVariable int id) {
        System.out.println("id: " + id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDemo> createUser(@RequestBody UserRegistrationApiDto userRegistrationApiDto) {
        return ResponseEntity.ok(userService.saveUserWithApi(userRegistrationApiDto));
    }

    @PutMapping("/user/{id}")
    public UserDemo updateUser(@PathVariable int id, @RequestBody UserUpdateApiDto userUpdateApiDto) {
        return userService.updateUserWithApi(userUpdateApiDto, id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserWithApi(id);
    }

    @GetMapping("/{userId}/roles")
    public Object getUserRoles(@PathVariable int userId) {
        Collection<com.example.spring_first_project.model.Role> roles = userService.getUserRoles(userId);
        System.out.println(roles);
        if (roles == null) {
            return ResponseEntity.notFound().build();
        }
        return roles;
    }
}
