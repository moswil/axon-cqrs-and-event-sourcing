package com.github.moswil.learn.bankacc.cmd.api.controllers;

import com.github.moswil.learn.bankacc.cmd.api.commands.DepositFundsCommand;
import com.github.moswil.learn.bankacc.cmd.api.dto.OpenAccountResponse;
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
@RequestMapping(path = "/api/v1/accounts/credit")
public class DepositFundsController {
    private final CommandGateway commandGateway;

    @Autowired
    public DepositFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody DepositFundsCommand command) {
        command.setId(id);
        try {
            this.commandGateway.send(command);

            return new ResponseEntity<>(
                    new BaseResponse("Successfully deposited " + command.getAmount()), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error when processing request to deposit funds for id - " + id;
            log.error(e.getMessage());
            System.out.println(safeErrorMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
