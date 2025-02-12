package com.blogapp.controllers;

import com.blogapp.payloads.API.ApiResponseDto;
import com.blogapp.payloads.User.UserDto;
import com.blogapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    // POST REQ -> CREATE USER
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> createUserHandler(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // POST REQ -> LOGIN USER
    public ResponseEntity<UserDto> loginUserHandler(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // DELETE REQ -> DELETE USER
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUserHandler(@PathVariable("userId") String userId){
        this.userService.deleteUser(userId);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,"User deleted Successfully.");
        return new ResponseEntity<ApiResponseDto>(apiResponseDto,HttpStatus.OK);
    }
    // PUT REQ -> UPDATE USER
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUserHandler(@Valid @RequestBody UserDto userDto,@PathVariable("userId") String userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
    }

    //GET REQ -> GET USER BY ID OR GET ALL USERS
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByIdHandler(@PathVariable("userId") String userId){
        UserDto fetchedUser = this.userService.getUserById(userId);
        return new ResponseEntity<>(fetchedUser,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUserHandler(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
