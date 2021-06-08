package com.github.moswil.learn.bankacc.cmd.api.aggregates;

import com.github.moswil.learn.bankacc.cmd.api.commands.CloseAccountCommand;
import com.github.moswil.learn.bankacc.cmd.api.commands.DepositFundsCommand;
import com.github.moswil.learn.bankacc.cmd.api.commands.OpenAccountCommand;
import com.github.moswil.learn.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.github.moswil.learn.bankacc.core.events.AccountClosedEvent;
import com.github.moswil.learn.bankacc.core.events.AccountOpenedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsDepositedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private BigDecimal balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        var event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .status(command.getStatus())
                .createdAt(new Date())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHolderId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        var amount = command.getAmount();
        var event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance.add(amount))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        this.balance = this.balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        var amount = command.getAmount();
        System.out.println(this.balance.subtract(amount));
        if (this.balance.subtract(amount).intValue() > 0) {
            var event = FundsWithdrawnEvent.builder()
                    .id(command.getId())
                    .amount(amount)
                    .balance(this.balance.subtract(amount))
                    .build();

            AggregateLifecycle.apply(event);
        }
        else {
            log.error("Insufficient Balance");
            throw new IllegalStateException("Withdraw declined,insufficient funds!");
        }
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        this.balance = this.balance.subtract(event.getAmount());
        System.out.println(this.balance);
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        var event = AccountClosedEvent.builder()
                .id(command.getId())
                .status(command.getStatus())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
