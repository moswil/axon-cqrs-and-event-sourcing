package com.github.moswil.learn.bankacc.query.api.controllers;

import com.github.moswil.learn.bankacc.query.api.dto.AccountLookupResponse;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAccountsByHolderIdQuery;
import com.github.moswil.learn.bankacc.query.api.queries.FindAllAccountsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {
            var query = new FindAllAccountsQuery();
            var response =  this.queryGateway
                    .query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request";

            log.error(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindAccountByIdQuery(id);
            var response =  this.queryGateway
                    .query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request";

            log.error(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/account-holder/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountAccountHolderId(
            @PathVariable(value = "accountHolderId") String accountHolderId) {

        try {
            var query = new FindAccountsByHolderIdQuery(accountHolderId);
            var response =  this.queryGateway
                    .query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request";

            log.error(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.NO_CONTENT);
        }
    }
}
