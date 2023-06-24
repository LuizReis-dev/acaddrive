package com.luizreis.acaddrive.dto.folder;

import java.util.UUID;

public class FolderIdDTO {

    private UUID id;

    public FolderIdDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
