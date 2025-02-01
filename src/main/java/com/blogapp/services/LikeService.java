package com.blogapp.services;

import com.blogapp.payloads.Like.LikeDto;

public interface LikeService {
    void toggleLikeOnPost(LikeDto likeDto);
    void toggleLikeOnComment(LikeDto likeDto);
}
