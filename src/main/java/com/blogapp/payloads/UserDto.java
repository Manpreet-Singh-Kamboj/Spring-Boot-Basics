package com.blogapp.payloads;

import com.blogapp.constants.Roles;
import com.blogapp.entities.Comment;
import com.blogapp.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;
    private String fullName;
    private String email;
    private String bio;
    private String password;
    private Roles role = Roles.USER;
}
