package com.luizreis.acaddrive.repositories;

import com.luizreis.acaddrive.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}
