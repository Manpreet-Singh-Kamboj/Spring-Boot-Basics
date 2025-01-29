package com.blogapp.exceptions;

public class InvalidPostOwnerException extends RuntimeException{
    String postId;
    String userId;

    public InvalidPostOwnerException(String postId,String userId) {
        super(String.format("User with ID %s is not the creator of Post with ID %s ",userId,postId));
        this.postId = postId;
        this.userId = userId;
    }
}
