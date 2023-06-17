package com.luizreis.acaddrive.repositories;

import com.luizreis.acaddrive.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}