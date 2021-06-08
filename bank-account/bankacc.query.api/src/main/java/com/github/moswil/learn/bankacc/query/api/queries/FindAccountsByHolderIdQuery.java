package com.github.moswil.learn.bankacc.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsByHolderIdQuery {
    private String accountHolderId;
}
