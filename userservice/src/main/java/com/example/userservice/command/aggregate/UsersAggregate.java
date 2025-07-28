package com.example.userservice.command.aggregate;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.example.userservice.command.command.UsersCreateCommand;
import com.example.userservice.command.command.UsersDeleteCommand;
import com.example.userservice.command.command.UsersUpdateCommand;
import com.example.userservice.command.event.UsersCreateEvent;
import com.example.userservice.command.event.UsersDeleteEvent;
import com.example.userservice.command.event.UsersUpdateEvent;

@Aggregate
public class UsersAggregate {
    private static final Logger logger = Logger.getLogger(UsersAggregate.class.getName());
    @AggregateIdentifier
    private String id;
    private String username;
    private String email;
    private String password_hash;
    private String full_name;
    private String avatar_url;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

    public UsersAggregate() {
        // Default constructor for Axon
    }

    @CommandHandler
    public UsersAggregate(UsersCreateCommand command) {
        UsersCreateEvent usersCreateEvent = new UsersCreateEvent();
        BeanUtils.copyProperties(command, usersCreateEvent);
        AggregateLifecycle.apply(usersCreateEvent);
    }

    @CommandHandler
    public void handle(UsersUpdateCommand command) {
        UsersUpdateEvent usersUpdateEvent = new UsersUpdateEvent();
        BeanUtils.copyProperties(command, usersUpdateEvent);
        AggregateLifecycle.apply(usersUpdateEvent);
    }

    @CommandHandler
    public void handle(UsersDeleteCommand command) {
        UsersDeleteEvent usersDeleteEvent = new UsersDeleteEvent();
        BeanUtils.copyProperties(command, usersDeleteEvent);
        AggregateLifecycle.apply(usersDeleteEvent);
    }

    @EventSourcingHandler
    public void on(UsersCreateEvent event) {
        this.id = event.getId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password_hash = event.getPassword_hash();
        this.full_name = event.getFull_name();
        this.avatar_url = event.getAvatar_url();
        this.create_at = event.getCreate_at();
        this.update_at = event.getUpdate_at();
    }

    @EventSourcingHandler
    public void on(UsersUpdateEvent event) {
        this.id = event.getId();
        if (event.getUsername() != null) this.username = event.getUsername();
        if (event.getEmail() != null) this.email = event.getEmail();
        if (event.getPassword_hash() != null) this.password_hash = event.getPassword_hash();
        if (event.getFull_name() != null) this.full_name = event.getFull_name();
        if (event.getAvatar_url() != null) this.avatar_url = event.getAvatar_url();
        this.update_at = event.getUpdate_at();
    }

    @EventSourcingHandler
    public void on(UsersDeleteEvent event) {
        this.id = event.getId();
    }

}
