package com.example.userservice.command.data;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    private String id;
    private String username;
    private String email;
    private String password_hash;
    private String full_name;
    private String bio;
    private String avatar_url;
    @Column(name = "created_at")
    private LocalDateTime create_at;
    @Column(name = "updated_at")
    private LocalDateTime update_at;
}
