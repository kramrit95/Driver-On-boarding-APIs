package com.project.driver.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.driver.Entity.Document;
import com.project.driver.Entity.DocumentType;
import com.project.driver.Entity.Driver;
import com.project.driver.exceptions.FileStorageException;
import com.project.driver.repo.DocumentRepository;
import com.project.driver.repo.DriverRepository;

import java.io.IOException;

@Service
public class DBStorageServiceImpl implements StorageService{

    private static final Logger logger = LoggerFactory.getLogger(DBStorageServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public String saveFile(MultipartFile file,Long driverId,DocumentType documentType) throws FileStorageException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Driver driver = driverRepository.getById(driverId);

            Document document = new Document(fileName, documentType, file.getBytes(),driver);
            documentRepository.save(document);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        } catch(Exception ex){
            logger.error("Exception occurred while saving file for driverId {} and docType {}",driverId,documentType,ex);
            throw ex;
        }
    }
}