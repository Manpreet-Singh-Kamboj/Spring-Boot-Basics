package com.blogapp.payloads.User;

import com.blogapp.constants.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationUserResponseDto {
    private String id;
    private String fullName;
    private String email;
    private String bio;
    private Roles role = Roles.USER;
}
