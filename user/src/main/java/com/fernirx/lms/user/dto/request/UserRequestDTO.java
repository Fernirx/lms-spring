package com.fernirx.lms.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private int roleId;
}
