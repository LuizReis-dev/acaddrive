package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.user.UserRequestDTO;
import com.luizreis.acaddrive.dto.user.UserResponseDTO;
import com.luizreis.acaddrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDTO insert(@RequestBody UserRequestDTO dto){
        return service.insert(dto);
    }
}
