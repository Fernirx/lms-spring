package com.fernirx.lms.auth.controller;

import com.fernirx.lms.auth.dto.request.OtpVerifyRequest;
import com.fernirx.lms.auth.dto.request.ForgotPasswordRequest;
import com.fernirx.lms.auth.dto.request.ResendOtpRequest;
import com.fernirx.lms.auth.dto.response.OtpVerifyResponse;
import com.fernirx.lms.auth.service.PasswordService;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.dtos.responses.SuccessResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.PASSWORD_PATH)
@AllArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping(ApiConstants.FORGOT_PASSWORD_PATH)
    public ResponseEntity<SuccessResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest resetPasswordRequest) {
        passwordService.forgotPassword(resetPasswordRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiMessages.RESET_PASSWORD_REQUEST_SUCCESS
        ));
    }

    @PostMapping(ApiConstants.RESEND_OTP_PATH)
    public ResponseEntity<SuccessResponse<Void>> resendOtp(@Valid @RequestBody ResendOtpRequest resendOtpRequest) {
        passwordService.resendOtp(resendOtpRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiMessages.RESEND_OTP_SUCCESS
        ));
    }

    @PostMapping(ApiConstants.VERIFY_OTP_PATH)
    public ResponseEntity<SuccessResponse<OtpVerifyResponse>> verifyOtp(@Valid @RequestBody OtpVerifyRequest otpVerifyRequest) {
        OtpVerifyResponse otpVerifyResponse = passwordService.verifyOtp(otpVerifyRequest);
        return ResponseEntity.ok(SuccessResponse.of(
                ApiMessages.OTP_VERIFY_SUCCESS,
                otpVerifyResponse
        ));
    }
}
