package com.example.spring_first_project.dto;

public class LoginResponse {

    private UserLoginDto userLoginDto;
    private String token;

    public LoginResponse(UserLoginDto userLoginDto, String token) {
        this.userLoginDto = userLoginDto;
        this.token = token;
    }

    public LoginResponse() {
    }

    public UserLoginDto getUserLoginDto() {
        return userLoginDto;
    }

    public void setUserLoginDto(UserLoginDto userLoginDto) {
        this.userLoginDto = userLoginDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
