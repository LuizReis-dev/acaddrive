package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.entities.File;
import com.luizreis.acaddrive.entities.Folder;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.repositories.FileRepository;
import com.luizreis.acaddrive.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository repository;
    private final FolderService folderService;
    private final UserService userService;

    public FileService(FileRepository repository, FolderService folderService, UserService userService) {
        this.repository = repository;
        this.folderService = folderService;
        this.userService = userService;
    }


    @Transactional
    public FileDTO insert(FileDTO file){
        File entity = new File();
        entity.setCreatedAt(file.getCreatedAt());
        entity.setName(file.getName());
        entity.setPath(file.getPath());
        entity.setSize(file.getSize());

        Folder folder = new Folder();
        folder.setId(file.getFolderId());
        User user = new User();
        user.setId(file.getUserId());

        entity.setFolder(folder);
        entity.setUser(user);

        return new FileDTO(repository.save(entity));
    }

    @Transactional
    public List<FileDTO> findFilesByFolder(UUID folderId) {
        if(!folderService.verifyPermissionInFolder(userService.getAuthenticatedUser().getId(), folderId)){
            throw new ForbiddenException("Not authorized");
        }
        List<FileDTO> files = repository.findFilesByFolder(folderId);
        return files;
    }
}
