package com.github.moswil.learn.bankacc.query.api.handlers;

import com.github.moswil.learn.bankacc.core.events.AccountClosedEvent;
import com.github.moswil.learn.bankacc.core.events.AccountOpenedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsDepositedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
