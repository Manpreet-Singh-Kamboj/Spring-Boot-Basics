package com.blogapp.payloads.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponseDto {
    private String token;
    private String refreshToken;
    private AuthenticationUserResponseDto user;
}
