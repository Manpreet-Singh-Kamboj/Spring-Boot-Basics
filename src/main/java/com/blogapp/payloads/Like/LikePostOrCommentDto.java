package com.blogapp.payloads.Like;

import com.blogapp.payloads.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikePostOrCommentDto {
    private String id;
    private UserDto user;
}
