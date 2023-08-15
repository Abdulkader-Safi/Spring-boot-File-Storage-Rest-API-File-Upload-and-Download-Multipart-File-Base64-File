package com.abdulkader.dbffilestore.service;

import com.abdulkader.dbffilestore.dao.FileStorageRepository;
import com.abdulkader.dbffilestore.entity.FileStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileStorageService {

    private final FileStorageRepository repository;

    public FileStorage persisFile(FileStorage file) {
        return repository.save(file);
    }

    public FileStorage retrieveFileByName(String fileName) {
        return repository.findByFileName(fileName);
    }

    public void removeFile(String fileName) throws Exception {
        FileStorage file = retrieveFileByName(fileName);
        if (file == null) {
            throw new Exception(String.format("File with name '%s' does not exist.", fileName));
        }
        repository.delete(file);
    }
}
