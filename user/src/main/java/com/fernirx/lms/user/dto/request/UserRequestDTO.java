package com.fernirx.lms.user.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(max = ValidationConstants.USERNAME_MAX_LENGTH,
            min = ValidationConstants.USERNAME_MIN_LENGTH,
            message = "Username must be between {min} and {max} character")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = ValidationConstants.PASSWORD_MAX_LENGTH,
            min = ValidationConstants.PASSWORD_MIN_LENGTH,
            message = "Password must be between {min} and {max} character")
    private String password;

    private int roleId;
}
