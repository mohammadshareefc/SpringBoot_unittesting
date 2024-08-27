package com.example.springboot_unit_testing.repository;

import com.example.springboot_unit_testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
