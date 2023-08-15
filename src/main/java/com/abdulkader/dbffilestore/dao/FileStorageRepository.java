package com.abdulkader.dbffilestore.dao;

import com.abdulkader.dbffilestore.entity.FileStorage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

    FileStorage findByFileName(String fileName);
}
