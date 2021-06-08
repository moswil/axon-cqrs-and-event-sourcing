package com.github.moswil.learn.bankacc.cmd.api.controllers;

import com.github.moswil.learn.bankacc.cmd.api.commands.CloseAccountCommand;
import com.github.moswil.learn.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/accounts")
public class CloseAccountController {
    private final CommandGateway commandGateway;

    @Autowired
    public CloseAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable("id") String id) {
        try {
            var command = CloseAccountCommand.builder()
                    .id(id)
                    .build();
            this.commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new BaseResponse("Successfully closed account " + id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            var safeErrorMessage = "Unable to close account with id " + id;
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
