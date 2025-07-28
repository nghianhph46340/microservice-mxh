package com.example.userservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRelationshipRepo extends JpaRepository<UsersRelationship, String> {
    
}
