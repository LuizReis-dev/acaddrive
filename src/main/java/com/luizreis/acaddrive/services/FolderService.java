package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.folder.FolderDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.entities.Folder;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.entities.UserFolder;
import com.luizreis.acaddrive.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class FolderService {

    private final FolderRepository repository;
    private final UserService userService;

    @Autowired
    public FolderService(FolderRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Transactional
    public FolderDTO create(FolderMinDTO dto){
        Folder folder = new Folder();
        folder.setName(dto.getName());
        folder.setCreated_at(Instant.now());
        User user = userService.getAuthenticatedUser();

        UserFolder userFolder = new UserFolder();
        userFolder.setFolder(folder);
        userFolder.setUser(user);
        userFolder.setCreator(true);

        folder.getUsers().add(userFolder);
        folder = repository.save(folder);
        return new FolderDTO(folder.getName(), user.getName(), Instant.now());
    }
}
