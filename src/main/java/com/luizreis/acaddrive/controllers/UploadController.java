package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.services.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final S3Service service;


    public UploadController(S3Service service) {
        this.service = service;
    }

    @PostMapping(value = "/{folderId}")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam(value="file") MultipartFile file, @PathVariable UUID folderId){
        return ResponseEntity.ok(service.uploadFile(file, folderId));
    }
}
