package com.example.spring_first_project.controller.authController;

import com.example.spring_first_project.dto.ApiResponse;
import com.example.spring_first_project.dto.LoginResponse;
import com.example.spring_first_project.dto.UserLoginDto;
import com.example.spring_first_project.service.JwtService;
import com.example.spring_first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody UserLoginDto userLoginDto) {
        try{
            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    userLoginDto.getEmail(),
                                    userLoginDto.getPassword()));

            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(new ApiResponse<>(
                        200,
                        "Generated token successful",
                        userService.login(userLoginDto, jwtService.generateToken(userLoginDto.getEmail()))));
            } else {
                throw new UsernameNotFoundException("Invalid user request!");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @PostMapping("/auth/generateToken")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticateAndGetToken(@RequestBody UserLoginDto authRequest) {
        try{
            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    authRequest.getEmail(),
                                    authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(new ApiResponse<>(
                        200,
                        "Generated token successful",
                        new LoginResponse(authRequest, jwtService.generateToken(authRequest.getEmail()))));
            } else {
                throw new UsernameNotFoundException("Invalid user request!");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }
}