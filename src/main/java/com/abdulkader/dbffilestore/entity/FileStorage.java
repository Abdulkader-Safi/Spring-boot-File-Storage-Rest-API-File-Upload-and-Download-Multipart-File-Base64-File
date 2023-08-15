package com.abdulkader.dbffilestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "filetype")
    private String filetype;

    @Column(name = "filebyte", length = 5000)
    private byte[] fileByte;

    @Column(name = "filebase64", length = 20000)
    private String fileBase64;
}
