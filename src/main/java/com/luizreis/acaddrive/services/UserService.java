package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.user.UserRequestDTO;
import com.luizreis.acaddrive.dto.user.UserResponseDTO;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.repositories.UserRepository;
import com.luizreis.acaddrive.services.exceptions.DbViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO insert(UserRequestDTO dto){
        try {
            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            return new UserResponseDTO(repository.save(user));
        }catch (DataIntegrityViolationException e){
            throw new DbViolationException("This email is already in use");
        }
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(user -> new UserResponseDTO(user));
    }
}
