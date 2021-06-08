package com.github.moswil.learn.bankacc.core.events;

import com.github.moswil.learn.bankacc.core.models.AccountStatus;
import com.github.moswil.learn.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {
    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private AccountStatus status;
    private Date createdAt;
    private BigDecimal openingBalance;

    public void setStatus(AccountStatus status) {
        this.status = AccountStatus.ACTIVE;
    }
}
