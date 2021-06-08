package com.github.moswil.learn.user.query.api.handlers;

import com.github.moswil.learn.user.core.events.UserRegisteredEvent;
import com.github.moswil.learn.user.core.events.UserRemovedEvent;
import com.github.moswil.learn.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
