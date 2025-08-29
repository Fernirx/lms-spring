package com.fernirx.lms.user.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank
    @Size(max = ValidationConstants.USERNAME_MAX_LENGTH,
            min = ValidationConstants.USERNAME_MIN_LENGTH)
    private String username;

    @NotBlank
    @Size(max = ValidationConstants.PASSWORD_MAX_LENGTH,
            min = ValidationConstants.PASSWORD_MIN_LENGTH)
    @Pattern(regexp = ValidationConstants.PASSWORD_PATTERN)
    private String password;

    @NotNull
    private int roleId;
}
