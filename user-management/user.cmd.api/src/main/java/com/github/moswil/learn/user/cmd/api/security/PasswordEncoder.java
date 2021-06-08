package com.github.moswil.learn.user.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
