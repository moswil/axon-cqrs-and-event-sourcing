package com.github.moswil.learn.user.cmd.api.commands;

import com.github.moswil.learn.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "no user details were provided.")
    @Valid
    private User user;
}
