package com.luizreis.acaddrive.dto.folder;

import com.luizreis.acaddrive.entities.Folder;

public class FolderMinDTO {

    private String name;

    public FolderMinDTO(){}

    public FolderMinDTO(String name) {
        this.name = name;
    }

    public FolderMinDTO(Folder folder){
        this.name = folder.getName();
    }

    public String getName() {
        return name;
    }
}
