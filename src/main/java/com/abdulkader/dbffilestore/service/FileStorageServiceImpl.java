package com.abdulkader.dbffilestore.service;

import com.abdulkader.dbffilestore.commons.FileStorageUtil;
import com.abdulkader.dbffilestore.entity.FileStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileStorageServiceImpl {

    private final FileStorageService fsService;

    public String uploadMultipartFile(MultipartFile data) {
        FileStorage file = new FileStorage();
        file.setFileName(UUID.randomUUID() + "-" + data.getOriginalFilename());
        file.setFiletype(data.getContentType());

        try {
            file.setFileByte(FileStorageUtil.compressFile(data.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileStorage newFile = fsService.persisFile(file);
        if (newFile != null) {
            return String.format("File %s uploaded successfully", data.getOriginalFilename());
        }

        return String.format("File %s failed to upload", data.getOriginalFilename());
    }

    public FileStorage retrieveFile(String filename) {
        return fsService.retrieveFileByName(filename);
    }

    public byte[] downloadMultipartFile(String filename) {
        return FileStorageUtil.deCompressFile(fsService.retrieveFileByName(filename).getFileByte());
    }

    public boolean deleteMultipartFile(String filename) {
        try {
            fsService.removeFile(filename);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
