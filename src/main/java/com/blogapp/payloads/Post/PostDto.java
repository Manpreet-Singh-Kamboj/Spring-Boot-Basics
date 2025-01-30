package com.blogapp.payloads.Post;

import com.blogapp.payloads.Category.CategoryDto;
import com.blogapp.payloads.Comment.CommentPostDto;
import com.blogapp.payloads.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private Set<CategoryDto> categories;
    private UserDto user;
    private Date addedDate;
    private List<CommentPostDto> comments;
}
