package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.folder.FolderDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService service;

    @Autowired
    public FolderController(FolderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FolderDTO> create(@RequestBody FolderMinDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }
}
