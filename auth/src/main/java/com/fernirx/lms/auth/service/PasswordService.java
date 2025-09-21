package com.fernirx.lms.auth.service;

import com.fernirx.lms.auth.dto.OTPData;
import com.fernirx.lms.auth.dto.request.OtpVerifyRequest;
import com.fernirx.lms.auth.dto.request.ForgotPasswordRequest;
import com.fernirx.lms.auth.dto.request.ResendOtpRequest;
import com.fernirx.lms.auth.dto.response.OtpVerifyResponse;
import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.OtpException;
import com.fernirx.lms.infrastructure.message.MailService;
import com.fernirx.lms.infrastructure.security.JwtProvider;
import com.fernirx.lms.user.entity.User;
import com.fernirx.lms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final UserService userService;
    private final OtpService otpService;

    public void forgotPassword(ForgotPasswordRequest request) {
        userService.getUserByEmailForReset(request.getEmail()).ifPresent(user -> {
            OTPData otp = otpService.generateOtp(user.getEmail());
            mailService.sendResetPassword(user.getEmail(), user.getUsername(), otp.getOtp());
        });
    }

    public void resendOtp(ResendOtpRequest request) {
        userService.getUserByEmailForReset(request.getEmail()).ifPresent(user -> {
            OTPData otp = otpService.regenerateOtp(user.getEmail());
            mailService.sendResetPassword(user.getEmail(), user.getUsername(), otp.getOtp());
        });
    }

    public OtpVerifyResponse verifyOtp(OtpVerifyRequest request) {
        otpService.validateOtp(request.getEmail(), request.getOtp());
        User user = userService.getUserByEmailForReset(request.getEmail())
                .orElseThrow(() -> new OtpException(
                        ErrorCode.OTP_INVALID,
                        ApiMessages.OTP_INVALID
                ));
        String resetPasswordToken =
                jwtProvider.generateResetPasswordToken(user.getId(), user.getUsername());

        return OtpVerifyResponse.builder()
                .resetPasswordToken(resetPasswordToken)
                .build();
    }
}
