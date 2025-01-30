package com.blogapp.services;

import com.blogapp.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    void deletePost(String postId,String userId);
    PostDto updatePost(PostDto postDto,String postId,String userId);
    List<PostDto> getUserPosts(String userId);
    List<PostDto> getPostByCategory(String categoryId);
}
