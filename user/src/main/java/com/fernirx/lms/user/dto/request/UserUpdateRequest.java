package com.fernirx.lms.user.dto.request;

import com.fernirx.lms.common.constants.ValidationConstants;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private Long roleId;
    @Size(max = ValidationConstants.USERNAME_MAX_LENGTH,
            min = ValidationConstants.USERNAME_MIN_LENGTH)
    private String username;
}
