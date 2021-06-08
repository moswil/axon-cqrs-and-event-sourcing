package com.github.moswil.learn.user.query.api.handlers;

import com.github.moswil.learn.user.core.events.UserRegisteredEvent;
import com.github.moswil.learn.user.core.events.UserRemovedEvent;
import com.github.moswil.learn.user.core.events.UserUpdatedEvent;
import com.github.moswil.learn.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        this.userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        this.userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        this.userRepository.deleteById(event.getId());
    }
}
