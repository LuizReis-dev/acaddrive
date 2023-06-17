package com.luizreis.acaddrive.dto.user;

import com.luizreis.acaddrive.entities.User;

import java.util.UUID;

public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;

    public UserResponseDTO(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
