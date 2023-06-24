package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.folder.FolderDTO;
import com.luizreis.acaddrive.dto.folder.FolderIdDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.entities.Folder;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.entities.UserFolder;
import com.luizreis.acaddrive.repositories.FolderRepository;
import com.luizreis.acaddrive.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

    public boolean verifyPermissionInFolder(UUID userId, UUID folderId) {
        List<FolderIdDTO> folders = repository.findFoldersByUser(userId);
        boolean belongs = false;
        for (FolderIdDTO folder : folders) {
            if (folder.getId().equals(folderId)) {
                belongs = true;
            }
        }
        return belongs;
    }

    @Transactional
    public FolderMinDTO findById(UUID id){
        Folder folder = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not Found!"));

        return new FolderMinDTO(folder.getName(),folder.getId());
    }
}
