package com.luizreis.acaddrive.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateDTO {

    @NotBlank(message="Name can't be empty")
    private String name;
    @NotBlank(message="Email can't be empty")
    @Email(message="Email invalid")
    private String email;
    public UserUpdateDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
