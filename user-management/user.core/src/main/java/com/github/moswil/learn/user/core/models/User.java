package com.github.moswil.learn.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotEmpty(message = "Firstname is required.")
    private String firstName;

    @NotEmpty(message = "Lastname is required.")
    private String lastName;

    @Email(message = "Email address should be valid.")
    @Indexed(name = "emailAddress", unique = true)
    private String emailAddress;

    @NotNull(message = "Please provide account credentials.")
    @Valid
    private Account account;
}
