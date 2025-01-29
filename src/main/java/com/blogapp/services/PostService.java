package com.blogapp.services;

import com.blogapp.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getUserPosts(String userId);
    List<PostDto> getPostByCategory(String categoryId);
}
