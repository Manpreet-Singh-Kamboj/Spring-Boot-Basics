package com.blogapp.payloads.Comment;

import com.blogapp.payloads.Post.PostCommentDto;
import com.blogapp.payloads.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentPostDto {
    private String id;
    private String comment;
    private List<CommentPostDto> replies = new ArrayList<>();
    private UserDto user;
}
