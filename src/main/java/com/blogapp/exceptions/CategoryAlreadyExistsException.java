package com.blogapp.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    String category;

    public CategoryAlreadyExistsException(String category){
        super(String.format("Category with name: %s already exists.",category));
        this.category = category;
    }
}
