package com.example.spring_first_project.service;

import com.example.spring_first_project.model.Role;
import com.example.spring_first_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void saveOrUpdate(Role role) {
        roleRepository.save(role);
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
