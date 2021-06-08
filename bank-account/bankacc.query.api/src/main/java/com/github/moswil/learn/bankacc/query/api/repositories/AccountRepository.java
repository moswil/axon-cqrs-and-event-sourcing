package com.github.moswil.learn.bankacc.query.api.repositories;

import com.github.moswil.learn.bankacc.core.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    Iterable<BankAccount> findByAccountHolderId(String accountHolderId);

    List<BankAccount> findByBalanceLessThan(double balance);

    List<BankAccount> findByBalanceGreaterThan(double balance);
}
