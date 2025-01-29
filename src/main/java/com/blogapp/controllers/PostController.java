package com.blogapp.controllers;

import com.blogapp.payloads.PostDto;
import com.blogapp.services.PostService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    // POST REQ -> CREATE POST -> ///TODO:ONLY AUTHENTICATED USER WILL BE ALLOWED
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPostHandler(@Valid @RequestBody PostDto postDto){
        PostDto createdPost = this.postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<PostDto>> getUserPostsHandler(@RequestParam("userId") String userId){
        List<PostDto> userPosts =  this.postService.getUserPosts(userId);
        return new ResponseEntity<>(userPosts,HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<PostDto>> getPostByCategoryHandler(@RequestParam("categoryId") String categoryId){
        List<PostDto> categoryPosts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(categoryPosts,HttpStatus.OK);
    }

}
