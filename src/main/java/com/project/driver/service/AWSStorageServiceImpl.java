package com.project.driver.service;




import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.driver.Entity.DocumentType;

@Service
public class AWSStorageServiceImpl implements StorageService{

    @Override
    public String saveFile(MultipartFile file, Long driverId, DocumentType docType){
        return null;
    }
}