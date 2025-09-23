package com.fernirx.lms.user.service;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.infrastructure.security.CustomUserDetails;
import com.fernirx.lms.user.entity.User;
import com.fernirx.lms.user.mapper.UserMapper;
import com.fernirx.lms.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameWithRole(username).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User",
                        "username",
                        username
                ));

        return userMapper.toCustomUserDetails(user);
    }
}
