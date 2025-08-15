package com.fernirx.lms.user.service;

import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.dtos.UserDTO;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    public List<UserDTO> getAllUser() {
       return userMapper.toListDto(userRepository.findAll());
    }

    public UserDTO getUserById(int id) {
        UserDTO user =  userMapper
                            .toDto(userRepository
                                    .findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("User",id)));
        return user;
    }

}
