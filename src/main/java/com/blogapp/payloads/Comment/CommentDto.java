package com.blogapp.payloads.Comment;

import com.blogapp.payloads.Post.PostCommentDto;
import com.blogapp.payloads.Post.PostDto;
import com.blogapp.payloads.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private String id;
    private String comment;
    private CommentPostDto parentComment;
    private PostCommentDto post;
    private UserDto user;
}
