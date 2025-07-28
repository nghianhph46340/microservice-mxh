package com.example.userservice.command.command;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class UsersUpdateCommand {
    @TargetAggregateIdentifier
    private String id;
    private String username;
    private String email;
    private String password_hash;
    private String full_name;
    private String avatar_url;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
