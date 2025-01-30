package com.blogapp.controllers;

import com.blogapp.payloads.API.ApiResponseDto;
import com.blogapp.payloads.Comment.CommentDto;
import com.blogapp.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    //POST REQ -> CREATE COMMENT -> ///TODO: ONLY LOGGED IN USERS CAN COMMENT
    @PostMapping("/create")
    public ResponseEntity<CommentDto> createCommentHandler(@Valid @RequestBody CommentDto commentDto){
        CommentDto savedComment = this.commentService.createComment(commentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    //PUT REQ -> UPDATE COMMENT -> ///TODO: ONLY LOGGED IN USERS CAN UPDATE COMMENT
    @PutMapping("/update")
    public ResponseEntity<CommentDto> updateCommentHandler(@RequestParam("commentId") String commentId, @RequestParam("userId") String userId,@RequestBody CommentDto commentDto){
        CommentDto updatedComment = this.commentService.updateComment(commentId,userId,commentDto.getComment());
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // DELETE REQ -> DELETE COMMENT ///TODO: ONLY LOGGED IN USERS CAN DELETE COMMENT
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDto> deleteCommentHandler(@RequestParam("commentId") String commentId,@RequestParam("userId") String userId){
        this.commentService.deleteComment(commentId,userId);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,"Comment deleted successfully.");
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

}
