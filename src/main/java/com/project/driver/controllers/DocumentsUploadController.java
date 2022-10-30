package com.project.driver.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.driver.Entity.DocumentType;
import com.project.driver.Entity.UploadFileResponse;
import com.project.driver.exceptions.FileStorageException;
import com.project.driver.exceptions.NoSuchDriverExistsException;
import com.project.driver.service.DriverService;
import com.project.driver.service.StorageService;

/**
 * Controller for handling API request for uploading documents.
 */

@RestController
public class DocumentsUploadController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentsUploadController.class);

    @Autowired
    @Qualifier("DBStorageServiceImpl")
    private StorageService storageService;

    @Autowired
    private DriverService driverService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("driverId") Long driverId,
                                         @RequestParam("docType") DocumentType docType) throws NoSuchDriverExistsException, FileStorageException {

        logger.info("Document upload for driverId {} and documentType {}", driverId, docType);
        try {
            String fileName = storageService.saveFile(file, driverId, docType);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();

            //once the documents are uploaded , setting documentsUploaded flag to true
            driverService.setDocumentsUploadedFlag(driverId);

            return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        }catch(Exception ex){
            logger.error("Exception while uploading document for driverId {} and documentType {}", driverId, docType);
            throw ex;
        }
    }

}