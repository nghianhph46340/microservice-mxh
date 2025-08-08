package com.example.userservice.command.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.command.command.UsersCreateCommand;
import com.example.userservice.command.command.UsersDeleteCommand;
import com.example.userservice.command.command.UsersUpdateCommand;
import com.example.userservice.command.data.UsersRepo;
import com.example.userservice.command.model.UsersRequest;


@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {
    private static final Logger logger = Logger.getLogger(UserCommandController.class.getName());
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private UsersRepo usersRepos;
    @GetMapping("/getAll")
    public String getMethodName() {
        return "Hello, " + "!";
    }

    @PostMapping("/add")
    public CompletableFuture<Object> add(@RequestBody UsersRequest entity) {
        if (usersRepos.findByUsername(entity.getUsername()).isPresent()) {
            return CompletableFuture.completedFuture("Error: Username already exists");
        }
        if (usersRepos.findByEmail(entity.getEmail()).isPresent()) {
            return CompletableFuture.completedFuture("Error: Email already exists");
        }

        UsersCreateCommand command = UsersCreateCommand.builder()
                .id(UUID.randomUUID().toString())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password_hash(entity.getPassword_hash())
                .full_name(entity.getFull_name())
                .bio(entity.getBio())
                .avatar_url(entity.getAvatar_url())
                .create_at(entity.getCreate_at())
                .build();
        logger.info("Sending UsersCreateCommand: " + command.getId());
        return commandGateway.send(command);
    }
    @PutMapping("/update/{id}")
    public CompletableFuture<Object> putMethodName(@PathVariable String id, @RequestBody UsersRequest entity) {
        //TODO: process PUT request
        UsersUpdateCommand command = new UsersUpdateCommand();
        command.setId(id);
        command.setUsername(entity.getUsername());
        command.setEmail(entity.getEmail());
        command.setPassword_hash(entity.getPassword_hash());
        command.setFull_name(entity.getFull_name());
        command.setAvatar_url(entity.getAvatar_url());
        command.setCreate_at(entity.getCreate_at());
        command.setUpdate_at(entity.getUpdate_at());
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<Object> deleteMethodName(@PathVariable String id) {
        UsersDeleteCommand command = new UsersDeleteCommand();
        command.setId(id);
        return commandGateway.send(command);
    }
}
