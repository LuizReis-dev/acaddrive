package com.luizreis.acaddrive.dto.auth;

public class AuthenticationRequestDTO {

    private String email;
    private String password;

    public AuthenticationRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
