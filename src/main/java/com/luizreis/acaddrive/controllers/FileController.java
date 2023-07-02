package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.services.FileService;
import com.luizreis.acaddrive.services.S3Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    private final S3Service service;
    private final FileService fileService;
    public FileController(S3Service service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping(value = "upload/{folderId}")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam(value="file") MultipartFile file, @PathVariable UUID folderId){
        return ResponseEntity.ok(service.uploadFile(file, folderId));
    }

    @GetMapping("/download/{folderName}/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable UUID folderName,
                                                          @PathVariable String fileName){
        byte[] file = service.downloadFile(folderName,fileName);
        ByteArrayResource resource = new ByteArrayResource(file);
        return ResponseEntity
                .ok()
                .contentLength(file.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/folder/{folderId}")
    public ResponseEntity<List<FileDTO>> findFilesByFolder(@PathVariable UUID folderId){
        List<FileDTO> files = fileService.findFilesByFolder(folderId);
        return ResponseEntity.ok(files);
    }

}
