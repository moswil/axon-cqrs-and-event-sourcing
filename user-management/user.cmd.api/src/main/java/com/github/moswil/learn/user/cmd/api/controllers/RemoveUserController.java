package com.github.moswil.learn.user.cmd.api.controllers;

import com.github.moswil.learn.user.cmd.api.commands.RemoveUserCommand;
import com.github.moswil.learn.user.core.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "REMOVE_USER_CONTROLLER")
@RestController
@RequestMapping(path = "/api/v1/users")
public class RemoveUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable("id") String id) {

        try {
            commandGateway.send(new RemoveUserCommand(id));

            return new ResponseEntity<>(new BaseResponse("User successfully removed!"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing remove user request for id: " + id;

            log.error(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
