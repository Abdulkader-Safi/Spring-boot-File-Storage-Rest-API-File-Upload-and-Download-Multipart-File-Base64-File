package com.abdulkader.dbffilestore.controller;

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

    @GetMapping("/multipart/{filename}")
    public ResponseEntity<?> downloadMultipartFile(@PathVariable String filename) {
        FileStorage fileDetails = fsServiceImpl.retrieveFile(filename);
        byte[] file = fsServiceImpl.downloadMultipartFile(filename);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype())).body(file);
    }

    @DeleteMapping("/multipart/{filename}")
    public ResponseEntity<?> deleteMultipartFile(@PathVariable String filename) {
        boolean delete = fsServiceImpl.deleteMultipartFile(filename);
        if (delete) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("File %s was deleted successfully", filename));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("File %s was delete failed", filename));
    }
}