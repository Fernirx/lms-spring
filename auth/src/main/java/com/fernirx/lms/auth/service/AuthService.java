package com.fernirx.lms.auth.service;

import com.fernirx.lms.auth.dto.request.LoginRequest;
import com.fernirx.lms.auth.dto.response.JwtResponse;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.exceptions.AccountDisabledException;
import com.fernirx.lms.common.exceptions.InvalidCredentialsException;
import com.fernirx.lms.infrastructure.message.MailService;
import com.fernirx.lms.infrastructure.security.CustomUserDetails;
import com.fernirx.lms.infrastructure.security.JwtProvider;
import com.fernirx.lms.infrastructure.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException(request.getUsername());
        } catch (DisabledException e) {
            throw new AccountDisabledException(request.getUsername());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        String username = userDetails.getUsername();
        String email = userDetails.getEmail();
        Set<String> roles = SecurityUtils.getAuthorities(userDetails);

        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(userId, username);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(ApiConstants.TOKEN_TYPE)
                .userId(userId)
                .username(username)
                .email(email)
                .roles(roles)
                .build();
    }
}
