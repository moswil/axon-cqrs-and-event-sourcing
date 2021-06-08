package com.github.moswil.learn.bankacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Builder
public class DepositFundsCommand {
    @TargetAggregateIdentifier
    private String id;

    @DecimalMin(value = "10", message = "Deposit funds should be at least 10")
    private BigDecimal amount;
}
