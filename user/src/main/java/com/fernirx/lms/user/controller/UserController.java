package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.user.dto.request.UserRequestDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
import com.fernirx.lms.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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
                    .status(HttpStatus.CONFLICT)
                    .body("");
        }
        else return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Create successful!");

    }
}
