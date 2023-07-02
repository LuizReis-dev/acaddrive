package com.luizreis.acaddrive.dto.folder;

import java.time.Instant;

public class FolderDTO {

    private String name;
    private String createdBy;
    private Instant createdAt;

    public FolderDTO() {
    }

    public FolderDTO(String name, String createdBy, Instant createdAt) {
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
