package com.tutorial.authService.services;

import org.springframework.stereotype.Service;

import com.tutorial.authService.models.User;
import com.tutorial.authService.repos.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User register(@NonNull User user) {
        return userRepository.save(user);
    }
}
