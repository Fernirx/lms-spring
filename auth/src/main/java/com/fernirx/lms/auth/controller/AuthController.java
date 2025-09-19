package com.fernirx.lms.auth.controller;

import com.fernirx.lms.auth.dto.request.LoginRequest;
import com.fernirx.lms.auth.dto.request.RefreshTokenRequest;
import com.fernirx.lms.auth.dto.request.ResetPasswordRequest;
import com.fernirx.lms.auth.dto.response.JwtResponse;
import com.fernirx.lms.auth.dto.response.RefreshTokenResponse;
import com.fernirx.lms.auth.service.AuthService;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import com.fernirx.lms.common.utils.ApiFormatter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.AUTH_PATH)
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiConstants.LOGIN_PATH)
    public ResponseEntity<SuccessResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiFormatter.userLoginSuccess(jwtResponse.getUsername()),
                jwtResponse
        ));
    }

    @PostMapping(ApiConstants.REFRESH_TOKEN_PATH)
    public ResponseEntity<SuccessResponse<RefreshTokenResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshTokenResponse refreshTokenResponse = authService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiMessages.REFRESH_TOKEN_SUCCESS,
                refreshTokenResponse
        ));
    }

    @PostMapping(ApiConstants.RESET_PASSWORD_REQUEST_PATH)
    public ResponseEntity<SuccessResponse<Void>> resetPasswordRequest(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.requestPasswordReset(resetPasswordRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiMessages.RESET_PASSWORD_REQUEST_SUCCESS
        ));
    }
}