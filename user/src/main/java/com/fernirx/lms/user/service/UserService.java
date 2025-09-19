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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByStatus(boolean deleted) {
        List<User> users = userRepository.findByIsDeleted(deleted);
        return userMapper.toListDto(users);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByEmailForReset(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest userRequest) {
        // Validate username uniqueness
        validateUsernameNotExists(userRequest.getUsername());

        // Build user entity from request
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.getRoleById(userRequest.getRoleId()));
        user.setIsDeleted(false);

        // Persist to database
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest userRequest) {
        // Retrieve existing user
        User user = findUserById(id);

        // Validate username change if applicable
        validateUsernameForUpdate(user, userRequest.getUsername());

        // Update basic fields
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());

        // Optional: Update role if provided
        if (userRequest.getRoleId() != null) {
            user.setRole(roleService.getRoleById(userRequest.getRoleId()));
        }

        // Persist changes to database
        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    @Transactional
    public Boolean softDeleteUser(Long id) {
        checkUserId(id);
        userRepository.softDeleteById(id);
        return true;
    }

    @Transactional
    public void restoreUser(Long id) {
        checkUserId(id);
        userRepository.restoreById(id);
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User",
                        "email",
                        email
                ));
    }

    private User findUserById(Long id) {
        return userRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User",
                        "id",
                        id
                ));
    }

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