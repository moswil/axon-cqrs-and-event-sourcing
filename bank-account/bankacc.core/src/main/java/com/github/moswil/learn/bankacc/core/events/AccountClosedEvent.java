package com.github.moswil.learn.bankacc.core.events;

import com.github.moswil.learn.bankacc.core.models.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountClosedEvent {
    private String id;

    private AccountStatus status;
}
