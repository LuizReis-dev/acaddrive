package com.luizreis.acaddrive.dto.folder;

import com.luizreis.acaddrive.entities.Folder;

import java.util.UUID;

public class FolderMinDTO {

    private UUID id;
    private String name;

    public FolderMinDTO(){}

    public FolderMinDTO(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    public FolderMinDTO(Folder folder){
        this.name = folder.getName();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
