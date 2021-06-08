package com.github.moswil.learn.bankacc.cmd.api.controllers;

import com.github.moswil.learn.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.github.moswil.learn.bankacc.core.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/accounts/debit")
public class WithdrawFundsController {
    private final CommandGateway commandGateway;

    @Autowired
    public WithdrawFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable("id") String id,
                                                      @Valid @RequestBody WithdrawFundsCommand command) {
        command.setId(id);
        try {
            var amount = command.getAmount();
            this.commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new BaseResponse("Successfully withdrawn " + amount), HttpStatus.OK);
        } catch (Exception e) {
            var safeError = "Error when processing request to withdraw funds for id - " + id;
            log.error(e.getMessage());
            return new ResponseEntity<>(new BaseResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
