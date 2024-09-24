package com.weather.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
