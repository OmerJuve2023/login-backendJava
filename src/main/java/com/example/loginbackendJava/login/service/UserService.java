package com.example.loginbackendJava.login.service;

import com.example.loginbackendJava.login.dto.UserRequest;
import com.example.loginbackendJava.login.dto.UserResponse;
import com.example.loginbackendJava.login.entity.Role;
import com.example.loginbackendJava.login.entity.User;
import com.example.loginbackendJava.login.repository.RoleRepository;
import com.example.loginbackendJava.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) throws UsernameNotFoundException {
        return UserResponse.toUserResponseFromUser(
                userRepository.findById(id).orElseThrow(
                        () -> new UsernameNotFoundException("User with id " + id + " not found")
                )
        );
    }

    public UserResponse createUser(UserRequest userRequest) {
        User newUser = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .build();

        return UserResponse.toUserResponseFromUser(userRepository.save(newUser));
    }

    public UserResponse deleteUser(Long id) {
        UserResponse userResponse =  this.getUserById(id);
        userRepository.deleteById(id);
        return userResponse;
    }
}
