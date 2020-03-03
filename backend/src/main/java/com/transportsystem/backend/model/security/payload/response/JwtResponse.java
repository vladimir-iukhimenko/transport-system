package com.transportsystem.backend.model.security.payload.response;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class JwtResponse {
    private final String type = "Bearer";

    @NotNull
    private String token;

    @NotNull
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private List<String> roles;

    public JwtResponse(String token, Integer id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
