package com.project.driver.service;

import com.project.driver.Entity.Driver;
import com.project.driver.exceptions.EmailAlreadyExistsException;
import com.project.driver.exceptions.NoSuchDriverExistsException;



public interface DriverService {

    Driver save(Driver driver) throws EmailAlreadyExistsException;

    Driver get(long driverId) throws NoSuchDriverExistsException;

    Driver markActive(long driverId) throws NoSuchDriverExistsException;

    Driver setVerifiedFlag(long driverId) throws NoSuchDriverExistsException;

    Driver setDocumentsUploadedFlag(long driverId) throws NoSuchDriverExistsException;
}
