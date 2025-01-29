package com.blogapp.payloads;

import com.blogapp.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private List<CategoryDto> categories;
    private UserDto user;
    private Date addedDate;
}
