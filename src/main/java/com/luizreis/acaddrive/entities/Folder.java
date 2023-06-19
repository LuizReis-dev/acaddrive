package com.luizreis.acaddrive.entities;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="tb_folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created_at;
    @OneToMany(mappedBy = "id.folder")
    private Set<UserFolder> users = new HashSet<>();

    public Folder() {
    }

    public Folder(UUID id, String name, Instant created_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Set<UserFolder> getUsers() {
        return users;
    }
}

