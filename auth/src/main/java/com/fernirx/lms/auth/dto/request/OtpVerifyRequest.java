package com.fernirx.lms.auth.dto.request;

import com.fernirx.lms.common.constants.SecurityConstants;
import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OtpVerifyRequest {
    @Email
    @NotBlank
    @Size(max = ValidationConstants.EMAIL_MAX_LENGTH,
            min = ValidationConstants.EMAIL_MIN_LENGTH)
    private String email;

    @NotBlank
    @Size(min = SecurityConstants.OTP_LENGTH,
            max = SecurityConstants.OTP_LENGTH)
    @Pattern(regexp = "\\d+", message = "OTP must contain only digits")
    private String otp;
}
