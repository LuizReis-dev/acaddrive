package com.luizreis.acaddrive.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {

    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Name can't be empty")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Name can't be empty")
    private String password;

    public RegisterRequestDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
