package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.annotations.docs.StandardResponseDoc;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.MessageConstants;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import com.fernirx.lms.user.dto.request.UserCreateDTO;
import com.fernirx.lms.user.dto.request.UserUpdateDTO;
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
    @StandardResponseDoc(value = "Get all user")
    public ResponseEntity<SuccessResponse<List<UserResponseDTO>>> getAllUser() {
        List<UserResponseDTO> users = userService.getActiveUsers();
        return ResponseEntity.ok(
                SuccessResponse.of(MessageConstants.SUCCESS_FETCH_DATA, users)
        );
    }

    @GetMapping("/{id}")
    @StandardResponseDoc(value = "Get a user", description = "Get a user by ID from Lms System")
    private ResponseEntity<SuccessResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_FETCH_DATA, user));
    }

    @PostMapping
    @StandardResponseDoc(
            value = "Create a user",
            description = "Create a new user in the Lms System with Username, Password, RoleId")
    public ResponseEntity<SuccessResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserCreateDTO user) {
        UserResponseDTO userResponse = userService.createUser(user);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_CREATE, userResponse));
    }

    @DeleteMapping("/{id}")
    @StandardResponseDoc(value = "Delete a user", description = "Delete a user by ID from Lms System")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_DELETE));
    }

    @PutMapping("/{id}")
    @StandardResponseDoc(value = "Update a user information", description = "Change user information from Lms System")
    public ResponseEntity<SuccessResponse<UserResponseDTO>> updateUser(@Valid @RequestBody UserUpdateDTO request,
                                                                       @PathVariable Long id) {
        UserResponseDTO user = userService.updateUser(request,id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_UPDATE, user));
    }
}
