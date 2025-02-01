package com.blogapp.payloads.Like;

import com.blogapp.constants.LikeType;
import com.blogapp.payloads.Comment.CommentDto;
import com.blogapp.payloads.Post.PostDto;
import com.blogapp.payloads.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
    private String id;
    private UserDto user;
    private PostDto post;
    private CommentDto comment;
    private LikeType likeType;
}
