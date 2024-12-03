package com.example.spring_first_project.controller.restController;

import com.example.spring_first_project.model.Role;
import com.example.spring_first_project.repository.RoleRepository;
import com.example.spring_first_project.service.RoleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestRoleController {

    private RoleServiceImpl roleServiceImpl;
    private RoleRepository roleRepository;

    public RestRoleController(RoleServiceImpl roleServiceImpl, RoleRepository roleRepository) {
        this.roleServiceImpl = roleServiceImpl;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleServiceImpl.getAllRoles();
    }

    @GetMapping("/roles/{name}")
    public Role getRole(@PathVariable String name) {
        return roleServiceImpl.getRoleByName(name);
    }
}
