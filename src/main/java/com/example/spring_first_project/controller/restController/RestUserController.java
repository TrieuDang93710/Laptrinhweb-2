package com.example.spring_first_project.controller.restController;

import com.example.spring_first_project.dto.*;
import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.model.Role;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.repository.CompanyRepository;
import com.example.spring_first_project.repository.UserRepository;
import com.example.spring_first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    private UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserApiResponse>> getUsers() {
        List<UserDemo> users = userService.findAll();
        List<UserApiResponse> userResponses = new ArrayList<>();
        UserApiResponse userApiResponse = null;
        for (UserDemo user : users) {
            if (user.getCompany() != null) {
                Company company = companyRepository.findById(user.getCompany().getId()).orElse(null);
                for(Role role : user.getAuthorities()){
                    Role rol = new Role(role.getAuthority());
                    userApiResponse = new UserApiResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            bCryptPasswordEncoder.encode(user.getPassword()),
                            company,
                            List.of(rol)
                    );
                }
                userResponses.add(userApiResponse);
            }else {
                for(Role role : user.getAuthorities()){
                    Role rol = new Role(role.getAuthority());
                    userApiResponse = new UserApiResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            bCryptPasswordEncoder.encode(user.getPassword()),
                            List.of(rol)
                    );
                }
                userResponses.add(userApiResponse);
            }

        }
        return  ResponseEntity.ok(userResponses);
    }

    @GetMapping("/users/string/{email}")
    public ResponseEntity<ApiResponse<UserDemo>> getUser(@PathVariable String email) {
        try{
            UserDemo user = userService.findByUsername(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "User not found", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "User found", user));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @GetMapping("/users/int/{id}")
    public ResponseEntity<ApiResponse<UserDemo>> findUserById(@PathVariable int id) {
        try{
            UserDemo user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "User not found", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "User found", user));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserDemo>> updateUser(@PathVariable int id, @RequestBody UserUpdateApiDto userUpdateApiDto) {
        try{
            UserDemo updateUser = userService.updateUserWithApi(userUpdateApiDto, id);
            return ResponseEntity.ok(new ApiResponse<>(200, "User just update", updateUser));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @PutMapping("/decentralization/{id}")
    public ResponseEntity<ApiResponse<UserDemo>> decentralizationUser(@PathVariable int id, @RequestBody DecentralizationDto decentralizationDto) {
        try{
            UserDemo updateUser = userService.decentralizationWithApi(decentralizationDto, id);
            return ResponseEntity.ok(new ApiResponse<>(200, "User just decentralization", updateUser));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserWithApi(id);
    }

    @GetMapping("/{userId}/roles")
    public Object getUserRoles(@PathVariable int userId) {
        Collection<Role> roles = userService.getUserRoles(userId);
        System.out.println(roles);
        if (roles == null) {
            return ResponseEntity.notFound().build();
        }
        return roles;
    }
}
