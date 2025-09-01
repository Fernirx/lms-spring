package com.fernirx.lms.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private RoleResponse role;
    private String username;
    private Boolean isDelete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
