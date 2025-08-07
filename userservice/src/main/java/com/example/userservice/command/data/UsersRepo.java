package com.example.userservice.command.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends  JpaRepository<Users, String>{
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
