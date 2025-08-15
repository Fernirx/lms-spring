package com.fernirx.lms.user.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private int roleId;
}
