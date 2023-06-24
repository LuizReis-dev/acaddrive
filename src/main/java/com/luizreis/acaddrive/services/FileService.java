package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.entities.File;
import com.luizreis.acaddrive.entities.Folder;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {

    private final FileRepository repository;

    public FileService(FileRepository repository) {
        this.repository = repository;
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

}
