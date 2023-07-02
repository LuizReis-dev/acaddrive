package com.luizreis.acaddrive.services;


import com.luizreis.acaddrive.dto.file.FileDTO;
import com.luizreis.acaddrive.dto.folder.FolderMinDTO;
import com.luizreis.acaddrive.entities.User;
import com.luizreis.acaddrive.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3;
    private final UserService userService;
    private final FolderService folderService;
    private final FileService fileService;

    public S3Service(S3Client s3, UserService userService, FolderService folderService, FileService fileService) {
        this.s3 = s3;
        this.userService = userService;
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @Value("${application.bucket.name}")
    private String bucketName;

    public FileDTO uploadFile(MultipartFile file, UUID folderId){
        Double size = (double) file.getSize();
        FolderMinDTO folder = folderService.findById(folderId);
        User user = userService.getAuthenticatedUser();
        if(!folderService.verifyPermissionInFolder(user.getId(), folder.getId())){
            throw new ForbiddenException("This user does not have access to this folder");
        }

        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        File uploadFile = convertMultipartFileToFile(file);
        String path = folder.getId().toString()+"/"+fileName;

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();
        s3.putObject(objectRequest, RequestBody.fromFile(uploadFile));

        uploadFile.delete();

        FileDTO fileDTO = new FileDTO(Instant.now(), fileName, path, size, folder.getId(), user.getId());
        fileDTO = fileService.insert(fileDTO);
        return fileDTO;
    }

    public byte[] downloadFile(UUID folder,String fileName){
        if(!folderService.verifyPermissionInFolder(userService.getAuthenticatedUser().getId(), folder)){
            throw  new ForbiddenException("Not authorized");
        }
        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(folder +"/"+ fileName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            return objectBytes.asByteArray();
        } catch (S3Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private File convertMultipartFileToFile(MultipartFile file){
        File convertdFile = new File(file.getOriginalFilename());

        try(FileOutputStream fos = new FileOutputStream(convertdFile)){
            fos.write(file.getBytes());
        }catch (IOException e){
            throw new RuntimeException("teste");
        }
        return convertdFile;
    }
}
