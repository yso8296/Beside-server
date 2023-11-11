package com.hackathon.beside.common.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
