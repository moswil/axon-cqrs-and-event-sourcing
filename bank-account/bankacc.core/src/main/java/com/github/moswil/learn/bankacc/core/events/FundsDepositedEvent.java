package com.github.moswil.learn.bankacc.core.events;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FundsDepositedEvent {
    private String id;
    private BigDecimal amount;
    private BigDecimal balance;
}
