package com.blogapp.services.implementations;

import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.User.UserDto;
import com.blogapp.entities.User;
import com.blogapp.exceptions.EmailAlreadyExistException;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation  implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImplementation(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

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
    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepository.deleteById(user.getId());
    }

    public UserDto updateUser(UserDto userDto, String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        if(userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());
        }
        if(userDto.getBio() != null){
            user.setBio(userDto.getBio());
        }
        if(userDto.getRole() != null){
            user.setRole(userDto.getRole());
        }
        if(userDto.getFullName() != null){
            user.setFullName(userDto.getFullName());
        }
        if(userDto.getPassword() != null){
            user.setPassword(userDto.getPassword());
        }
        this.userRepository.save(user);
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(user->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }
}
