package com.fernirx.lms.user.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private int id;
    private int roleId;
    private String username;
    private boolean isEnable;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
