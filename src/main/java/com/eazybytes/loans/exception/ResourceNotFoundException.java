package com.eazybytes.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  // If we havmn't used This annotation then the status will 500 internal error when this exception throws.
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String  fieldName, String fieldValue){
        super(String.format("%s not found for %s = '%s'",resource, fieldName, fieldValue));
    }
}
