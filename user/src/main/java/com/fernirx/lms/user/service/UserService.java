package com.fernirx.lms.user.service;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.DuplicateEntryException;
import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.dto.request.UserCreateRequest;
import com.fernirx.lms.user.dto.request.UserUpdateRequest;
import com.fernirx.lms.user.dto.response.UserResponse;
import com.fernirx.lms.user.entity.Role;
import com.fernirx.lms.user.entity.User;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getUsersByStatus(boolean status) {
        List<User> users = userRepository.findUsersByIsDelete(status);
        return userMapper.toListDto(users);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "user",
                        "id",
                        id
                ));

        return userMapper.toDto(user);
    }

    public UserResponse createUser(UserCreateRequest userRequest) {
        // Validate username uniqueness
        validateUsernameNotExists(userRequest.getUsername());

        // Build user entity from request
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password for security
        user.setRole(roleService.getRoleById(userRequest.getRoleId()));

        // Persist to database
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserResponse updateUser(Long id, UserUpdateRequest userRequest) {
        // Retrieve existing user
        User user = userRepository.getUserById(id);

        // Validate username change if applicable
        validateUsernameForUpdate(user, userRequest.getUsername());

        // Update basic fields
        user.setUsername(userRequest.getUsername());

        // Optional: Update role if provided
        if (userRequest.getRoleId() != null) {
            user.setRole(roleService.getRoleById(userRequest.getRoleId()));
        }

        // Optional: Update password if provided
        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        // Persist changes to database
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public Boolean softDeleteUser(Long id) {
        checkUserId(id);

        // Mark user as deleted instead of removing from DB
        User user = userRepository.getUsersById(id);
        user.setIsDelete(true);
        userRepository.save(user);

        return true;
    }

    public Boolean hardDeleteUser(Long id) {
        checkUserId(id);
        userRepository.removeUserById(id); // Permanent removal
        return true;
    }

    public void restoreUser(Long id) {
        checkUserId(id);

        User user = userRepository.getUserById(id);

        // Early return if user is already active
        if (!user.getIsDelete()) {
            return;
        }

        // Restore user to active status
        user.setIsDelete(false);
        userRepository.save(user);
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private void checkUserId(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    ErrorCode.USER_NOT_FOUND,
                    "user",
                    "id",
                    id
            );
        }
    }

    private Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private void validateUsernameNotExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateEntryException(
                    ErrorCode.USERNAME_EXISTS,
                    "user",
                    "username",
                    username
            );
        }
    }

    private void validateUsernameForUpdate(User user, String newUsername) {
        // Check if username is actually being changed
        boolean isUsernameChanged = !user.getUsername().equals(newUsername);
        boolean isNewUsernameExists = checkUsername(newUsername);

        // Only throw exception if changing to an existing username
        if (isUsernameChanged && isNewUsernameExists) {
            throw new DuplicateEntryException(
                    ErrorCode.USERNAME_EXISTS,
                    "user",
                    "username",
                    newUsername
            );
        }
    }
}