package com.fernirx.lms.auth.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @Email
    @NotBlank
    @Size(max = ValidationConstants.EMAIL_MAX_LENGTH,
            min = ValidationConstants.EMAIL_MIN_LENGTH)
    private String email;
}
