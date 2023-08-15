package com.abdulkader.dbffilestore.controller;

import com.abdulkader.dbffilestore.dto.FileStorageDTO;
import com.abdulkader.dbffilestore.entity.FileStorage;
import com.abdulkader.dbffilestore.service.FileStorageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/file-store")
public class FileStorageController {

    private final FileStorageServiceImpl fsServiceImpl;

    @PostMapping("/multipart")
    public ResponseEntity<?> uploadMultipartFile(@RequestParam("file") MultipartFile data) {
        String response = fsServiceImpl.uploadMultipartFile(data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/base64")
    public ResponseEntity<?> uploadBase64File(@RequestBody FileStorageDTO data) {
        String response = fsServiceImpl.uploadBase64File(data.getFileName(), data.getFiletype(), data.getFileBase64());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/generale/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFileData, @RequestBody FileStorageDTO base64FileData) {
        if (multipartFileData != null) {
            return uploadMultipartFile(multipartFileData);
        } else {
            return uploadBase64File(base64FileData);
        }
    }

    @GetMapping("/multipart/{filename}")
    public ResponseEntity<?> downloadMultipartFile(@PathVariable String filename) {
        FileStorage fileDetails = fsServiceImpl.retrieveFile(filename);
        byte[] file = fsServiceImpl.downloadMultipartFile(filename);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype())).body(file);
    }

    @GetMapping("/base64/{filename}")
    public ResponseEntity<?> downloadBase64File(@PathVariable String filename) {
        FileStorage fileDetails = fsServiceImpl.retrieveFile(filename);
        byte[] file = fsServiceImpl.downloadBase64File(filename);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype())).body(file);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        FileStorage fileDetails = fsServiceImpl.retrieveFile(filename);
        if (fileDetails != null) {
            byte[] file = fsServiceImpl.downloadMultipartFile(filename);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype())).body(file);
        } else {
            byte[] file = fsServiceImpl.downloadBase64File(filename);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype())).body(file);
        }
    }

    @GetMapping("/base64/{filename}/details")
    public ResponseEntity<?> getBase64FileDetails(@PathVariable String filename) {
        FileStorage fileDetails = fsServiceImpl.retrieveFile(filename);

        return ResponseEntity.status(HttpStatus.OK).body(fileDetails);
    }
}
