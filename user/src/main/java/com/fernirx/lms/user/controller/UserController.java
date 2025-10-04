package com.fernirx.lms.user.controller;

import com.fernirx.lms.common.annotations.docs.StandardResponseDoc;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import com.fernirx.lms.common.utils.ApiFormatter;
import com.fernirx.lms.user.dto.request.UserCreateRequest;
import com.fernirx.lms.user.dto.request.UserUpdateRequest;
import com.fernirx.lms.user.dto.response.UserResponse;
import com.fernirx.lms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @StandardResponseDoc(value = "Get users by status")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPT_HEAD', 'ACAD_AFFAIRS')")
    public ResponseEntity<SuccessResponse<List<UserResponse>>> getUsersByStatus(
            @RequestParam(required = false, defaultValue = "false") boolean status) {
        List<UserResponse> users = userService.getUsersByStatus(status);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourcesRetrieved("User"),
                users
        ));
    }

    @GetMapping("/{id}")
    @StandardResponseDoc(
            value = "Get a user",
            description = "Get a user by ID from LMS System"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPT_HEAD', 'ACAD_AFFAIRS') or #id == authentication.principal.id")
    public ResponseEntity<SuccessResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourcesRetrieved("User"),
                user
        ));
    }

    @PostMapping
    @StandardResponseDoc(
            value = "Create a user",
            description = "Create a new user in the LMS System with Username, Password, RoleId"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'ACAD_AFFAIRS')")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(
            @Valid @RequestBody UserCreateRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourceCreated("User"),
                userResponse
        ));
    }

    @PutMapping("/{id}")
    @StandardResponseDoc(
            value = "Update user information",
            description = "Update user information in the LMS System"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'ACAD_AFFAIRS')")
    public ResponseEntity<SuccessResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourceUpdated("User"),
                user
        ));
    }

    @DeleteMapping("/{id}")
    @StandardResponseDoc(
            value = "Delete a user",
            description = "Soft delete a user by ID from LMS System"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourceDeleted("User")
        ));
    }

    @PutMapping("/{id}/restore")
    @StandardResponseDoc(
            value = "Restore user",
            description = "Restore user activity status"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SuccessResponse<Void>> restoreUser(@PathVariable Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.resourceUpdated("User")
        ));
    }
}