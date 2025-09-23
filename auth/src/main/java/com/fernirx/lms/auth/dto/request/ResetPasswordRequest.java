package com.fernirx.lms.auth.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotEmpty
    private final String resetPasswordToken;

    @NotBlank
    @Size(max = ValidationConstants.PASSWORD_MAX_LENGTH,
            min = ValidationConstants.PASSWORD_MIN_LENGTH)
    @Pattern(regexp = ValidationConstants.PASSWORD_PATTERN)
    private String password;
}
