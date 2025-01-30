package com.blogapp.services;

import com.blogapp.payloads.Comment.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(String commentId,String userId,String comment);
    void deleteComment(String commentId,String userId);
}
