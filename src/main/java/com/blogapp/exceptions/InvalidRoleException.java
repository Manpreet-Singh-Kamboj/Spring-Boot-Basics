package com.blogapp.exceptions;

public class InvalidRoleException extends RuntimeException {
    String role;
    public InvalidRoleException(String role){
        super(String.format("%s is not a valid role.",role));
        this.role = role;
    }
}
