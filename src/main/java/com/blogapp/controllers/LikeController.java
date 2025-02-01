package com.blogapp.controllers;

import com.blogapp.payloads.API.ApiResponseDto;
import com.blogapp.payloads.Like.LikeDto;
import com.blogapp.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }
    @PostMapping("/post")
    public ResponseEntity<ApiResponseDto> postLikeController(@RequestBody LikeDto likeDto){
        this.likeService.toggleLikeOnPost(likeDto);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,"Post Like Success");
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    @PostMapping("/comment")
    public ResponseEntity<ApiResponseDto> commentLikeController(@RequestBody LikeDto likeDto){
        this.likeService.toggleLikeOnComment(likeDto);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,"Comment Like Success");
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
