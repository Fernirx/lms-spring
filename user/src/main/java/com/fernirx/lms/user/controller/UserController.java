package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.annotations.docs.StandardResponseDoc;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.MessageConstants;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import com.fernirx.lms.user.dto.request.UserRequestDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
import com.fernirx.lms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @StandardResponseDoc(value="Get all user")
    public ResponseEntity<SuccessResponse<List<UserResponseDTO>>> getAllUser() {
        List<UserResponseDTO> users = userService.getActiveUsers();
        return ResponseEntity.ok(
                SuccessResponse.of("User retrieved successfully",users)
        );
    }

    @GetMapping("/{id}")
    @StandardResponseDoc(value="Get a user",description = "Get a user by ID from Lms System")
    private ResponseEntity<SuccessResponse<UserResponseDTO>> getUserById(@PathVariable int id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(SuccessResponse.of("User retrieved successfully",user));
    }

    @PostMapping
    @StandardResponseDoc(
            value="Create a user",
            description = "Create a new user in the Lms System with Username, Password, RoleId")
    public ResponseEntity<SuccessResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO user) {
        UserResponseDTO userResponse = userService.createUser(user);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_CREATE,userResponse));
    }

    @DeleteMapping("/{id}")
    @StandardResponseDoc(value="Delete a user",description = "Delete a user by ID from Lms System")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable int id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_DELETE));
    }
}
