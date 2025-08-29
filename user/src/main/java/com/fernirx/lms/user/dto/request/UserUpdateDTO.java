package com.fernirx.lms.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Integer id;
    private Integer roleId;
    private String username;
    private String password;
    private Boolean isDelete;
}
