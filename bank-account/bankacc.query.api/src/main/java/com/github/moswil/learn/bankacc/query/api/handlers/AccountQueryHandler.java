package com.github.moswil.learn.bankacc.query.api.handlers;

import com.github.moswil.learn.bankacc.query.api.dto.AccountLookupResponse;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountsByHolderIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountsByAccountHolderId(FindAccountsByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
