package com.luizreis.acaddrive.repositories;

import com.luizreis.acaddrive.dto.folder.FolderIdDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {

    @Query("SELECT new com.luizreis.acaddrive.dto.folder.FolderMinDTO(obj.id.folder.name,obj.id.folder.id) FROM UserFolder obj WHERE obj.id.user.id =:userId")
    List<FolderMinDTO> findFoldersByUser(UUID userId);
}
