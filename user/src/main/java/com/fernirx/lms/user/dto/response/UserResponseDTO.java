package com.fernirx.lms.user.dto.response;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserResponseDTO {
    private int id;
    private int roleId;
    private String username;
    private boolean isEnable;
    private ZonedDateTime createdAt;
    private ZonedDateTime updateAt;
}
