package com.github.moswil.learn.bankacc.cmd.api.commands;

import com.github.moswil.learn.bankacc.core.models.AccountStatus;
import com.github.moswil.learn.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class OpenAccountCommand {
    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No account holder ID was supplied.")
    private String accountHolderId;

    @NotNull(message = "No account type was supplied.")
    private AccountType accountType;

    @DecimalMin(value = "50", message = "Opening Balance must be at least $50.00")
    private BigDecimal openingBalance;

    private AccountStatus status;

    public AccountStatus getStatus() {
        return AccountStatus.ACTIVE;
    }
}
