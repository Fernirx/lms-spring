package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.dtos.UserDTO;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import com.fernirx.lms.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiConstants.USERS_PATH)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
