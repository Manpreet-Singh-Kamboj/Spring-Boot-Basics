package com.blogapp.controllers;

import com.blogapp.payloads.ApiResponseDto;
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

    // DELETE REQ -> DELETE USER POST -> ///TODO: ONLY POST OWNER AND AUTHORIZED USER CAN DELETE THE POST
    @DeleteMapping("/delete")
    public  ResponseEntity<ApiResponseDto> deletePostHandler(@RequestParam("postId") String postId, @RequestParam("userId") String userId){
        this.postService.deletePost(postId,userId);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,"Post deleted successfully.");
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

    // UPDATE REQ -> UPDATE USER POST -> ///TODO: ONLY POST OWNER AND AUTHORIZED USER CAN UPDATE THE POST
    @PutMapping("/update")
    public ResponseEntity<PostDto> updatePostHandler(@RequestParam("postId") String postId, @RequestParam("userId")String userId, @Valid @RequestBody PostDto postDto){
        PostDto updatedPost = this.postService.updatePost(postDto,postId,userId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


    // GET REQ -> GET USER's POST -> ///TODO: NEED TO IMPLEMENT ONLY AUTHENTICATED USERS ACCESS THIS API.
    @GetMapping("/user")
    public ResponseEntity<List<PostDto>> getUserPostsHandler(@RequestParam("userId") String userId){
        List<PostDto> userPosts =  this.postService.getUserPosts(userId);
        return new ResponseEntity<>(userPosts,HttpStatus.OK);
    }

    // GET REQ -> GET POST BY CATEGORY
    @GetMapping("/category")
    public ResponseEntity<List<PostDto>> getPostByCategoryHandler(@RequestParam("categoryId") String categoryId){
        List<PostDto> categoryPosts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(categoryPosts,HttpStatus.OK);
    }

    // GET REQ -> GET POST BY ID
    @GetMapping("/get")
    public ResponseEntity<PostDto> getPostByIdHandler(@RequestParam("postId") String postId){
        PostDto fetchedPost = this.postService.getPostById(postId);
        return new ResponseEntity<>(fetchedPost,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> allPosts = this.postService.getAllPosts();
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

}
