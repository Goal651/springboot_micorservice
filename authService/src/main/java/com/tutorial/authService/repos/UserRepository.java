package com.tutorial.authService.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.authService.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
