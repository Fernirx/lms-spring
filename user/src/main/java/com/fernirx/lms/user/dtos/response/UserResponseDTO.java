package com.fernirx.lms.user.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private int id;
    private int roleId;
    private String username;
    private boolean isEnable;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
