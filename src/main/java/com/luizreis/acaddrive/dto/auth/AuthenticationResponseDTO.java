package com.luizreis.acaddrive.dto.auth;

import java.util.UUID;

public class AuthenticationResponseDTO {

    private UUID id;
    private String token;

    public AuthenticationResponseDTO(String token, UUID id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public UUID getId() {
        return id;
    }
}
