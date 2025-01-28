package com.blogapp.controllers;

import com.blogapp.payloads.UserDto;
import com.blogapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    // POST REQ -> CREATE USER
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> createUserHandler(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // DELETE REQ -> DELETE USER

    // PUT REQ -> UPDATE USER

    //GET REQ -> GET USER BY ID OR GET ALL USERS
}
