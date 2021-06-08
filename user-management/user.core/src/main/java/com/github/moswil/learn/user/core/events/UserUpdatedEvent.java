package com.github.moswil.learn.user.core.events;

import com.github.moswil.learn.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
