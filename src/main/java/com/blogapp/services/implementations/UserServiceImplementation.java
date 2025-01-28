package com.blogapp.services.implementations;

import com.blogapp.payloads.UserDto;
import com.blogapp.entities.User;
import com.blogapp.exceptions.EmailAlreadyExistException;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if(this.userRepository.existsByEmail(userDto.getEmail())){
            ///TODO: Add Appropriate Exception
            throw new EmailAlreadyExistException("User","email",userDto.getEmail());
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
