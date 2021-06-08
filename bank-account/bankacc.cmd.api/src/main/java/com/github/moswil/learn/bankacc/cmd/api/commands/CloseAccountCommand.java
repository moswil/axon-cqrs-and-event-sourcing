package com.github.moswil.learn.bankacc.cmd.api.commands;

import com.github.moswil.learn.bankacc.core.models.AccountStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CloseAccountCommand {
    @TargetAggregateIdentifier
    private String id;

    private AccountStatus status;

    public AccountStatus getStatus() {
        return AccountStatus.CLOSED;
    }
}
