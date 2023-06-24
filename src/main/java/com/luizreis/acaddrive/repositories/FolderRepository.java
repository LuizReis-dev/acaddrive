package com.luizreis.acaddrive.repositories;

import com.luizreis.acaddrive.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
