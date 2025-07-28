package com.example.userservice.command.data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users_relationship")
@Data
public class UsersRelationship {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "id")
    private Users following;
    
    private String status; // "following", "blocked", "requested"
    private LocalDateTime created_at;
}
