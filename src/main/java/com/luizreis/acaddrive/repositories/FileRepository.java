package com.luizreis.acaddrive.repositories;

import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

    @Query("SELECT new com.luizreis.acaddrive.dto.file.FileDTO(obj) FROM File obj WHERE obj.folder.id =:folderId")
    List<FileDTO> findFilesByFolder(UUID folderId);
}
