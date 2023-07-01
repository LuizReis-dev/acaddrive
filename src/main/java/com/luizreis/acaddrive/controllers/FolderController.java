package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.folder.FolderDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.services.FolderService;
import com.luizreis.acaddrive.services.UserService;
import com.luizreis.acaddrive.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService service;
    private final UserService userService;
    @Autowired
    public FolderController(FolderService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<FolderDTO> create(@RequestBody FolderMinDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FolderMinDTO>> getFoldersByUser(@PathVariable UUID userId){
        if (userService.isUserOrAdmin(userId)){
            List<FolderMinDTO> folders = service.getFoldersByUser(userId);
            return ResponseEntity.ok(folders);
        }
        throw new ForbiddenException("Not authorized");

    }
}
