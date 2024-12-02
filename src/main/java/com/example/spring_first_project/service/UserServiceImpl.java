package com.example.spring_first_project.service;

import com.example.spring_first_project.dto.UserRegistrationDto;
import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.model.Role;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.repository.CompanyRepository;
import com.example.spring_first_project.repository.RoleRepository;
import com.example.spring_first_project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CompanyRepository companyRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            RoleRepository roleRepository,
            CompanyRepository companyRepository
    ) {
        super();
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<UserDemo> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDemo save(UserRegistrationDto userRegistrationDto) {

        Company company = new Company("Company 1", new ArrayList<UserDemo>());
        companyRepository.save(company);

        UserDemo user = new UserDemo();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));

        user.setCompany(company);

        Role role = new Role("ROLE_USER");
        user.setAuthorities(Arrays.asList(role));
        roleRepository.save(role);

        company.getUsers().add(user);

        // Lưu người dùng
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDemo user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }
}
