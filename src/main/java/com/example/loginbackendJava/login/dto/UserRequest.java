package com.example.loginbackendJava.login.dto;

import com.example.loginbackendJava.login.entity.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;

    public UserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    /*private Set<Role> authority;*/

    /*public UserRequest(String username, String email, String password, Set<Role> authority) {
        this.username = username;
        this.email = email;
        this.password = password;
*//*
        if (authority == null) {
            this.authority = new HashSet<>();
        }
        else {
            this.authority = authority;
        }*//*
    }*/

    /*public UserRequest(String username, String email, String password) {
        this(username, email, password);
    }*/
}
