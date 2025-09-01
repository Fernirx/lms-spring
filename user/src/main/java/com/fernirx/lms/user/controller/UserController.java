package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.annotations.docs.StandardResponseDoc;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.MessageConstants;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import com.fernirx.lms.user.dto.request.UserCreateRequest;
import com.fernirx.lms.user.dto.request.UserUpdateRequest;
import com.fernirx.lms.user.dto.response.UserResponse;
import com.fernirx.lms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @StandardResponseDoc(value = "Get users by status")
    public ResponseEntity<SuccessResponse<List<UserResponse>>> getUsersByStatus(@RequestParam String status) {
        List<UserResponse> users= userService.getUsersByStatus(status);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_FETCH_DATA, users));
    }

    @GetMapping("/{id}")
    @StandardResponseDoc(value = "Get a user", description = "Get a user by ID from Lms System")
    public ResponseEntity<SuccessResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_FETCH_DATA, user));
    }

    @PostMapping
    @StandardResponseDoc(
            value = "Create a user",
            description = "Create a new user in the Lms System with Username, Password, RoleId")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(@Valid @RequestBody UserCreateRequest user) {
        UserResponse userResponse = userService.createUser(user);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_CREATE, userResponse));
    }

    @PutMapping("/{id}")
    @StandardResponseDoc(value = "Update a user information", description = "Change user information from Lms System")
    public ResponseEntity<SuccessResponse<UserResponse>> updateUser(@Valid @PathVariable Long id,
                                                                    @RequestBody UserUpdateRequest request) {
        UserResponse user = userService.updateUser(id,request);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_UPDATE, user));
    }

    @DeleteMapping("/{id}")
    @StandardResponseDoc(value = "Delete a user", description = "Delete a user by ID from Lms System")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_DELETE));
    }

    @PutMapping("/{id}/restore")
    @StandardResponseDoc(value = "Restore user activity status", description = "Restore user activity status ")
    public ResponseEntity<SuccessResponse<Void>> restoreUser(@PathVariable Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok(SuccessResponse.of(MessageConstants.SUCCESS_UPDATE));
    }
}
