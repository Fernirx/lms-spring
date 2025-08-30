package com.fernirx.lms.user.service;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.DuplicateEntryException;
import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.dto.request.UserCreateDTO;
import com.fernirx.lms.user.dto.request.UserUpdateDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
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

    public List<UserResponseDTO> getActiveUsers() {
        return userMapper.toListDto(userRepository.findUsersByIsDelete(false));
    }

    public List<UserResponseDTO> getAllUser() {
        return userMapper.toListDto(userRepository.findAll());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    public UserResponseDTO createUser(UserCreateDTO userRequest) {
        if (userRepository.existsByUsername((userRequest.getUsername())))
            throw new DuplicateEntryException(ErrorCode.USERNAME_ALREADY_EXISTS);

        User user = userMapper.toEntity(userRequest);
        Role role = roleService.getRoleById(userRequest.getRoleId());
        String passwordEncoded = passwordEncoder.encode(user.getPassword());

        user.setRole(role);
        user.setPassword(passwordEncoded);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void checkUserId(Long id) {
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND);
    }

    public Boolean softDeleteUser(Long id) {
        checkUserId(id);
        User user = userRepository.getUsersById(id);
        user.setIsDelete(true);
        userRepository.save(user);
        return true;
    }

    public Boolean hardDeleteUser(Long id) {
        checkUserId(id);
        userRepository.removeUserById(id);
        return true;
    }

    public UserResponseDTO updateUser(UserUpdateDTO userRequest) {
        checkUserId(userRequest.getId());

        User user = userRepository.getUsersById(userRequest.getId());

        if (userRequest.getRoleId() != null) {
            Role role = roleService.getRoleById(userRequest.getRoleId());
            user.setRole(role);
        }
        if (userRequest.getPassword() != null) {
            String passwordEncoded = passwordEncoder.encode(userRequest.getPassword());
            user.setPassword(passwordEncoded);
        }
        if (userRequest.getUsername() != null) user.setUsername(userRequest.getUsername());
        if (userRequest.getIsDelete() != null) user.setIsDelete(userRequest.getIsDelete());

        userRepository.save(user);

        return userMapper.toDto(user);
    }

}
