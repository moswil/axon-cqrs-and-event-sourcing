package com.github.moswil.learn.bankacc.query.api.handlers;

import com.github.moswil.learn.bankacc.core.models.BankAccount;
import com.github.moswil.learn.bankacc.query.api.dto.AccountLookupResponse;
import com.github.moswil.learn.bankacc.query.api.dto.EqualityType;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountsByHolderIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAllAccountsQuery;
import com.github.moswil.learn.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var bankAccount = this.accountRepository.findById(query.getId());
        return bankAccount.map(account -> new AccountLookupResponse("Bank Account Found.", account))
                .orElseGet(() -> new AccountLookupResponse("No Bank Account Found for ID - " + query.getId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsByAccountHolderId(FindAccountsByHolderIdQuery query) {
        var bankAccountsIterator = this.accountRepository.findByAccountHolderId(query.getAccountHolderId());

        if (!bankAccountsIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No Bank Accounts were Found!");
        }

        var bankAccounts = new ArrayList<BankAccount>();

        bankAccountsIterator.forEach(bankAccounts::add);

        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully Returned " + count + " Account(s)", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountsIterator = this.accountRepository.findAll();

        if (!bankAccountsIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No Bank Accounts were Found!");
        }

        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountsIterator.forEach(account -> bankAccounts.add(account));
        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully Returned " + count + " Account(s)", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        var bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN
                ? this.accountRepository.findByBalanceGreaterThan(query.getBalance())
                : this.accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccounts != null && bankAccounts.size() > 0
                ? new AccountLookupResponse("Successfully Returned " + bankAccounts.size() + " Account(s)", bankAccounts)
                : new AccountLookupResponse("No Bank Accounts were Found!");
    }
}
