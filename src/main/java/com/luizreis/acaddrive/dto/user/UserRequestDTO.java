package com.luizreis.acaddrive.dto.user;

import com.luizreis.acaddrive.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {

    @NotBlank(message="Name can't be empty")
    private String name;
    @NotBlank(message="Email can't be empty")
    @Email(message="Email invalid")
    private String email;
    @NotBlank(message="Password can't be empty")
    private String password;

    public UserRequestDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserRequestDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
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
