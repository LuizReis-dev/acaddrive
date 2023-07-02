package com.luizreis.acaddrive.dto.file;

import com.luizreis.acaddrive.entities.File;

import java.time.Instant;
import java.util.UUID;

public class FileDTO {

    private UUID id;
    private Instant createdAt;
    private String name;
    private String path;
    private Double size;
    private UUID folderId;
    private UUID userId;

    public FileDTO(UUID id, Instant createdAt, String name, String path, Double size, UUID folderId, UUID userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.path = path;
        this.size = size;
        this.folderId = folderId;
        this.userId = userId;
    }

    public FileDTO(Instant createdAt, String name, String path, Double size, UUID folderId, UUID userId) {
        this.createdAt = createdAt;
        this.name = name;
        this.path = path;
        this.size = size;
        this.folderId = folderId;
        this.userId = userId;
    }

    public FileDTO(File file){
        id = file.getId();
        createdAt = file.getCreatedAt();
        name = file.getName();
        path = file.getPath();
        size = file.getSize();
        folderId = file.getFolder().getId();
        userId = file.getUser().getId();
    }
    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Double getSize() {
        return size;
    }

    public UUID getFolderId() {
        return folderId;
    }

    public UUID getUserId() {
        return userId;
    }
}
