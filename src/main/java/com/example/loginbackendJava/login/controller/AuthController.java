package com.example.loginbackendJava.login.controller;


import com.example.loginbackendJava.login.dto.LoginRequest;
import com.example.loginbackendJava.login.dto.UserRequest;
import com.example.loginbackendJava.login.dto.UserResponse;
import com.example.loginbackendJava.login.entity.User;
import com.example.loginbackendJava.login.security.JwtUtils;
import com.example.loginbackendJava.login.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAuthorities()));
    }

    @PostMapping("signup")
    public UserResponse signupUser(@RequestBody @Valid UserRequest request) {
        log.debug("Register: " + request.toString());
        return userService.createUser(request);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }
}
