package com.github.moswil.learn.bankacc.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount implements Serializable {
    @Id
    private String id;

    private String accountHolderId;

    private Date createdAt;

    private AccountType accountType;

    private AccountStatus status;

    private double balance;

}
