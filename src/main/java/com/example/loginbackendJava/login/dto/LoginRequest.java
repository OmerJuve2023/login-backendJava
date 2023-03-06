package com.example.loginbackendJava.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LoginRequest {
    private String username;
    private String password;
}
