package com.fernirx.lms.user.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private int roleId;
}
