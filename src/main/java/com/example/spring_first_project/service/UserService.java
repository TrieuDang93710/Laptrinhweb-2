package com.example.spring_first_project.service;

import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveOrUpdate(UserDemo user) {
        userRepository.save(user);
    }

    public List<UserDemo> getAllUser() {
        return userRepository.findAll();
    }

    public UserDemo getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void deleteUserById(int id) {
        UserDemo user = userRepository.findById(id).orElse(null);

        if (user != null && user.getCompany() != null) {
            user.getCompany().setUsers(null);
            user.setCompany(null);
        }
        userRepository.deleteById(id);
    }
}