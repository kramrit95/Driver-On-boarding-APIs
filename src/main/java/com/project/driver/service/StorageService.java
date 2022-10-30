package com.project.driver.service;




import org.springframework.web.multipart.MultipartFile;

import com.project.driver.Entity.DocumentType;
import com.project.driver.exceptions.FileStorageException;

public interface StorageService {

    String saveFile(MultipartFile file, Long driverId, DocumentType docType) throws FileStorageException;
}


