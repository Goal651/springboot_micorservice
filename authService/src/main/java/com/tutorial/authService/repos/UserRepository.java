package com.tutorial.authService.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.authService.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
