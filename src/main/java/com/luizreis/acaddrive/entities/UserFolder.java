package com.luizreis.acaddrive.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.core.annotation.Order;

@Entity
@Table(name="tb_user_folder")
public class UserFolder {

    @EmbeddedId
    private UserFolderPK id = new UserFolderPK();
    private boolean creator;

    public UserFolder() {
    }

    public UserFolder(User user,Folder folder, boolean creator) {
        this.id.setUser(user);
        this.id.setFolder(folder);
        this.creator = creator;
    }

    public User getUser(){
        return id.getUser();
    }

    public void setUser(User user){
        id.setUser(user);
    }

    public Folder getFolder(){
        return id.getFolder();
    }

    public void setFolder(Folder folder){
        id.setFolder(folder);
    }

    public boolean isCreator() {
        return creator;
    }
}
