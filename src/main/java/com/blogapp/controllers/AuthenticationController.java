package com.blogapp.controllers;

import com.blogapp.payloads.User.AuthenticationRequestDto;
import com.blogapp.payloads.User.AuthenticationResponseDto;
import com.blogapp.payloads.User.UserDto;
import com.blogapp.services.implementations.AuthenticationServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationServiceImplementation authenticationService;
    public AuthenticationController(AuthenticationServiceImplementation authenticationService){
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserDto userDto) {
        try {
            AuthenticationResponseDto res = authenticationService.register(userDto);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody AuthenticationRequestDto authenticationRequest) {
        try {
            AuthenticationResponseDto res = authenticationService.login(authenticationRequest);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refresh(@RequestParam("token") String refreshToken) {
        try {
            AuthenticationResponseDto res = authenticationService.refreshToken(refreshToken);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
        try {
            Boolean res = authenticationService.validateToken(token);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
