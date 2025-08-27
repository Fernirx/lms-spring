package com.fernirx.lms.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private int id;
    private RoleResponseDTO role;
    private String username;
    private Boolean isDelete;
    private Instant createdAt;
    private Instant updatedAt;
}
