package com.blogapp.services.implementations;

import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.User.AuthenticationRequestDto;
import com.blogapp.payloads.User.AuthenticationResponseDto;
import com.blogapp.payloads.User.AuthenticationUserResponseDto;
import com.blogapp.payloads.User.UserDto;
import com.blogapp.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    public AuthenticationResponseDto register(@Valid UserDto registerRequest) {
        User convert = this.modelMapper.map(registerRequest,User.class);
        User user = User.builder()
                .fullName(convert.getFullName())
                .email(convert.getEmail())
                .password(passwordEncoder.encode(convert.getPassword()))
                .role(convert.getRole())
                .bio(convert.getBio())
                .build();
        User savedUser = this.userRepository.save(user);

        var jwtToken = jwtService.generateToken(new HashMap<>(),savedUser);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), savedUser);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .user(this.modelMapper.map(user, AuthenticationUserResponseDto.class))
                .build();
    }
    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User","Email",authenticationRequest.getEmail()));
        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponseDto
                .builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .user(this.modelMapper.map(user, AuthenticationUserResponseDto.class))
                .build();
    }

    public AuthenticationResponseDto refreshToken(String refreshToken) {
        var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken)).orElseThrow(() -> new ResourceNotFoundException("User","Email",jwtService.getEmailFromToken(refreshToken)));
        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponseDto
                .builder()
                .token(jwtToken)
                .refreshToken(newRefreshToken)
                .user(this.modelMapper.map(user, AuthenticationUserResponseDto.class))
                .build();
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
