package com.blogapp.exceptions;

public class InvalidCommentOwnerException extends RuntimeException {
    String commentId;
    String userId;

    public InvalidCommentOwnerException(String commentId,String userId) {
        super(String.format("User with ID %s is not the creator of Comment with ID %s ",userId,commentId));
        this.commentId = commentId;
        this.userId = userId;
    }
}
