package com.transportsystem.backend.model.security.payload.response;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MessageResponse {

    @NotNull
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
