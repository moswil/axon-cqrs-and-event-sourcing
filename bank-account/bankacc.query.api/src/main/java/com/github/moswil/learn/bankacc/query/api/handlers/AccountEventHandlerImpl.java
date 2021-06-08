package com.github.moswil.learn.bankacc.query.api.handlers;

import com.github.moswil.learn.bankacc.core.events.AccountClosedEvent;
import com.github.moswil.learn.bankacc.core.events.AccountOpenedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsDepositedEvent;
import com.github.moswil.learn.bankacc.core.events.FundsWithdrawnEvent;
import com.github.moswil.learn.bankacc.core.models.BankAccount;
import com.github.moswil.learn.bankacc.query.api.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .createdAt(event.getCreatedAt())
                .accountType(event.getAccountType())
                .status(event.getStatus())
                .balance(event.getOpeningBalance().doubleValue())
                .build();
        this.accountRepository.save(bankAccount);
    }

//    private void updateBalance(T event) {
//        var bankAccount = this.accountRepository.findById(event.getId());
//
//        if (bankAccount.isEmpty()) return;
//
//        bankAccount.get().setBalance(event.getBalance());
//        accountRepository.save(bankAccount.get());
//    }

    @EventHandler
    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = this.accountRepository.findById(event.getId());

        log.info(bankAccount.toString());

        if (bankAccount.isEmpty()) return;

        bankAccount.get().setBalance(event.getBalance().doubleValue());
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(FundsWithdrawnEvent event) {
        var bankAccount = this.accountRepository.findById(event.getId());

        log.info(bankAccount.toString());

        if (bankAccount.isEmpty()) return;

        bankAccount.get().setBalance(event.getBalance().doubleValue());
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(AccountClosedEvent event) {
        var bankAccount = this.accountRepository.findById(event.getId());

        if (bankAccount.isEmpty()) return;

        bankAccount.get().setStatus(event.getStatus());

        accountRepository.save(bankAccount.get());

//        this.accountRepository.deleteById(event.getId());
    }
}
