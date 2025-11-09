package com.tutorial.authService.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tutorial.authService.models.User;
import com.tutorial.authService.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User savedUser = new User();
        savedUser.setName("john doe");
        savedUser.setEmail("john.doe@example.com");
        return authService.register(savedUser);
    }
}
