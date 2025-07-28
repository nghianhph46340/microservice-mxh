package com.example.userservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class UsersDeleteCommand {
    @TargetAggregateIdentifier
    private String id;
}
