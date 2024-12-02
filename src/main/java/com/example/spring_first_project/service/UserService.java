package com.example.spring_first_project.service;

import com.example.spring_first_project.dto.UserRegistrationDto;
import com.example.spring_first_project.model.UserDemo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService extends UserDetailsService {
    List<UserDemo> findAll();
    UserDemo save(UserRegistrationDto userRegistrationDto);
}