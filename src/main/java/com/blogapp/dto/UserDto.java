package com.blogapp.dto;

import com.blogapp.constants.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
