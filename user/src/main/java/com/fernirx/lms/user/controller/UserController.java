package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCategory;
import com.fernirx.lms.user.dtos.request.UserRequestDTO;
import com.fernirx.lms.user.dtos.response.UserResponseDTO;
import com.fernirx.lms.user.service.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user) {
        if(userService.createUser(user) == null) {
            return ResponseEntity
                    .status(ErrorCategory.CONFLICT.getHttpStatus())
                    .body(ErrorCategory.CONFLICT.getDescription());
        }
        else return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Create successful!");

    }
}
