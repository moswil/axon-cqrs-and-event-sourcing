package com.github.moswil.learn.user.cmd.api.dto;

import com.github.moswil.learn.user.core.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
