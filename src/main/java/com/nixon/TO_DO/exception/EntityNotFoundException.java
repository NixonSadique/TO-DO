package com.nixon.TO_DO.exception;

public class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(String message){
        super(message);
    }
}
