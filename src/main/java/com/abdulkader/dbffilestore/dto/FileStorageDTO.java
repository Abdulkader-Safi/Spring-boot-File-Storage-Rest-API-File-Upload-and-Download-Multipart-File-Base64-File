package com.abdulkader.dbffilestore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageDTO {

    private String fileName;

    private String filetype;

    private String fileBase64;
}
