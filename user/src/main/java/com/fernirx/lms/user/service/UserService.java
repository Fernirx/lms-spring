package com.fernirx.lms.user.service;

import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.dtos.request.UserRequestDTO;
import com.fernirx.lms.user.dtos.response.UserResponseDTO;
import com.fernirx.lms.user.entity.User;
import com.fernirx.lms.user.mapper.UserRequestMapper;
import com.fernirx.lms.user.mapper.UserResponseMapper;
import com.fernirx.lms.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public UserService(UserRepository userRepository,
                       UserResponseMapper userResponseMapper,
                       UserRequestMapper userRequestMapper) {
        this.userRepository=userRepository;
        this.userResponseMapper=userResponseMapper;
        this.userRequestMapper=userRequestMapper;
    }

    public List<UserResponseDTO> getAllUser() {
       return userResponseMapper.toListDto(userRepository.findAll());
    }

    public UserResponseDTO getUserById(int id) {
        UserResponseDTO user =  userResponseMapper
                            .toDto(userRepository
                                    .findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("User",id)));
        return user;
    }

    public User createUser(UserRequestDTO userRequest) {
        if(userRepository.findUserByUsername(userRequest.getUsername()) != null)
            return null;

        User user = userRequestMapper.toEntity(userRequest);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setEnable(false);

        return  userRepository.save(user);
    }

}
