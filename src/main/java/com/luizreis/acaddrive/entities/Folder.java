package com.luizreis.acaddrive.entities;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name="tb_folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", name = "created_at")
    private Instant createdAt;
    @OneToMany(mappedBy = "id.folder")
    private Set<UserFolder> users = new HashSet<>();

    @OneToMany(mappedBy = "folder")
    private List<File> files = new ArrayList<>();

    public Folder() {
    }

    public Folder(UUID id, String name, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreated_at(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<UserFolder> getUsers() {
        return users;
    }

    public List<File> getFiles() {
        return files;
    }
}

