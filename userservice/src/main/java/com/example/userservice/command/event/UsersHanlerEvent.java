package com.example.userservice.command.event;

import java.util.logging.Logger;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.userservice.command.data.Users;
import com.example.userservice.command.data.UsersRepo;

@Component
public class UsersHanlerEvent {
    private static final Logger logger = Logger.getLogger(UsersHanlerEvent.class.getName());
    @Autowired
    private UsersRepo usersRepo;

    @EventHandler
    public void on(UsersCreateEvent event) {
        logger.info("Handling UsersCreateEvent: " + event.getId());
        Users users = new Users();
        BeanUtils.copyProperties(event, users);
        usersRepo.save(users);
    }

    @EventHandler
    public void on(UsersUpdateEvent event) {
        usersRepo.findById(event.getId()).ifPresent(users -> {
            if (event.getUsername() != null) users.setUsername(event.getUsername());
            if (event.getEmail() != null) users.setEmail(event.getEmail());
            if (event.getPassword_hash() != null) users.setPassword_hash(event.getPassword_hash());
            if (event.getFull_name() != null) users.setFull_name(event.getFull_name());
            if (event.getAvatar_url() != null) users.setAvatar_url(event.getAvatar_url());
            // create_at is not updated
            if (event.getUpdate_at() != null) users.setUpdate_at(event.getUpdate_at());
            
            usersRepo.save(users);
            logger.info("User updated: " + users.getId());
        });
    }
    @EventHandler
    public void on(UsersDeleteEvent event) {
        Users users = usersRepo.findById(event.getId()).orElse(null);
        if (users != null && users.getId() != null) {
            usersRepo.delete(users);
        }
    }

}
