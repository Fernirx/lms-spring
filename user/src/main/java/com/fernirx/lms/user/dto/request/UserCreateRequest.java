package com.fernirx.lms.user.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Size(max = ValidationConstants.USERNAME_MAX_LENGTH,
            min = ValidationConstants.USERNAME_MIN_LENGTH)
    private String username;

    @NotBlank
    @Size(max = ValidationConstants.PASSWORD_MAX_LENGTH,
            min = ValidationConstants.PASSWORD_MIN_LENGTH)
    @Pattern(regexp = ValidationConstants.PASSWORD_PATTERN)
    private String password;

    @Email
    @NotBlank
    @Size(max = ValidationConstants.EMAIL_MAX_LENGTH,
            min = ValidationConstants.EMAIL_MIN_LENGTH)
    private String email;

    @NotNull
    private Long roleId;
}
