package com.blogapp.services;

import com.blogapp.payloads.User.AuthenticationRequestDto;
import com.blogapp.payloads.User.AuthenticationResponseDto;
import com.blogapp.payloads.User.UserDto;
import jakarta.validation.Valid;

public interface AuthenticationService {
    AuthenticationResponseDto register(@Valid UserDto registerRequest);
    AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest);

    AuthenticationResponseDto refreshToken(String refreshToken);

    Boolean validateToken(String token);
}
