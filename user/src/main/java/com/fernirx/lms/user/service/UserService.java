package com.fernirx.lms.user.service;

import com.fernirx.lms.user.dto.request.UserRequestDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
import com.fernirx.lms.user.entity.User;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAllUser() {
       return userMapper.toListDto(userRepository.findAll());
    }

    public UserResponseDTO getUserById(int id) {
        UserResponseDTO user =  userMapper
                            .toDto(userRepository
                                    .findById(id)
                                    .orElseThrow());
        return user;
    }

    public boolean isExists(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    public User createUser(UserRequestDTO userRequest) {
        if(isExists(userRequest.getUsername()))
            return null;

        User user = userMapper.toEntity(userRequest);
        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdatedAt(ZonedDateTime.now());
        user.setIsDelete(false);

        return  userRepository.save(user);
    }

}
