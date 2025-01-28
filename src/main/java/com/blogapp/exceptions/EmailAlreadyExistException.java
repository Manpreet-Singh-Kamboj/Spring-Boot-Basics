package com.blogapp.exceptions;

public class EmailAlreadyExistException extends  RuntimeException{
    String resourceName;
    String fieldName;
    String email;
    public EmailAlreadyExistException(String resourceName,String fieldName,String email){
        super(String.format("%s with %s: %s already exists",resourceName,fieldName,email));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.email = email;
    }
}
