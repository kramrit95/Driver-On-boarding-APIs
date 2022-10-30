package com.project.driver.exceptions;



/**
 * Wrapper exception if phone number already exists in system
 */
public class PhoneNumberAlreadyExistsException extends Exception{

    public PhoneNumberAlreadyExistsException(String message){
        super(message);
    }
}