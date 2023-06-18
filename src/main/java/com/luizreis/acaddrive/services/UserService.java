package com.luizreis.acaddrive.services;

import com.luizreis.acaddrive.dto.user.UserRequestDTO;
import com.luizreis.acaddrive.dto.user.UserResponseDTO;
import com.luizreis.acaddrive.dto.user.UserUpdateDTO;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.repositories.UserRepository;
import com.luizreis.acaddrive.services.exceptions.DbViolationException;
import com.luizreis.acaddrive.services.exceptions.ForbiddenException;
import com.luizreis.acaddrive.services.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.logging.Logger;

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

    @Transactional(readOnly = true)
    public UserResponseDTO findById(UUID id) {
        if(!isUserOrAdmin(id)){
            throw new ForbiddenException("Not authorized");
        }
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserResponseDTO(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("User not found!");
        }
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DbViolationException("Integrity violation error");
        }
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO dto) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("User not found!");
        }
        if(!isUserOrAdmin(id)){
            throw new ForbiddenException("Not authorized");
        }
        User user = repository.getReferenceById(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user = repository.save(user);
        return new UserResponseDTO(user);
    }

    public boolean isUserOrAdmin(UUID id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = repository.findByEmail(userName)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        String role = user.getRole().toString();
        if(role.equals("ADMIN") || id.equals(user.getId())) return true;

        return false;

    }
}
