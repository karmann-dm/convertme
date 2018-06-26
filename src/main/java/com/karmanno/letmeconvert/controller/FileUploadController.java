package com.karmanno.letmeconvert.controller;

import com.karmanno.letmeconvert.model.UploadedFile;
import com.karmanno.letmeconvert.payload.response.ApiResponse;
import com.karmanno.letmeconvert.repository.UploadedFileRepository;
import com.karmanno.letmeconvert.security.CurrentUser;
import com.karmanno.letmeconvert.security.UserPrincipal;
import com.karmanno.letmeconvert.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file")MultipartFile uploadFile, @CurrentUser UserPrincipal userPrincipal) {
        logger.info("Upload started...");
        if(uploadFile.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Please, select a file!"), HttpStatus.BAD_REQUEST);
        }

        try {
            UploadedFile uploadedFile = storageService.store(uploadFile);
            uploadedFileRepository.save(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Could not save the file :(");
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Could not save the file"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Stored success!"), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("file") String fileName, @CurrentUser UserPrincipal userPrincipal) {
        logger.info("Download started...");
        try {
            logger.info("File name: " + fileName);
            logger.info("Principal ID: " + userPrincipal.getId());
            UploadedFile uploadedFile = uploadedFileRepository.findByName(fileName).get();
            Resource file = storageService.loadAsResource(uploadedFile.getPathToFile());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Failed to download file"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
