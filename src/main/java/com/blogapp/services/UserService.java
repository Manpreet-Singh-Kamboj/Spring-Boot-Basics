package com.blogapp.services;

import com.blogapp.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    void deleteUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, String userId);
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
}
