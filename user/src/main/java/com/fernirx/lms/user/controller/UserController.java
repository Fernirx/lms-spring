package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.user.dtos.UserDTO;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiConstants.USERS_PATH)
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository,
                          UserMapper userMapper) {
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userMapper.toListDto(userRepository.findAll()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userMapper.toDto(userRepository.findById(id).orElseThrow()));
    }
}
