package com.github.moswil.learn.bankacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Builder
public class WithdrawFundsCommand {
    @TargetAggregateIdentifier
    private String id;

    @DecimalMin(value = "10", message = "Minimum withdrawal amount is 10")
    @DecimalMax(value = "10000", message = "Maximum withdrawal amount is 10000")
    private BigDecimal amount;
}
