package com.blogapp.services.implementations;

import com.blogapp.dto.UserDto;
import com.blogapp.entities.User;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation  implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto createUser(UserDto userDto) {
        if(this.userRepository.existsByEmail(userDto.getEmail())){
            ///TODO: Add Appropriate Exception
        }
        User user = this.modelMapper.map(userDto,User.class);
        User createdUser = this.userRepository.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public void deleteUser(UserDto userDto) {

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }
}
