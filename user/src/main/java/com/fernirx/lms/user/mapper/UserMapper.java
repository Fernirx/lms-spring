package com.fernirx.lms.user.mapper;


import com.fernirx.lms.user.dto.request.UserRequestDTO;
import com.fernirx.lms.user.dto.response.RoleResponseDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
import com.fernirx.lms.user.entity.Role;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
//  User
    User toEntity(UserRequestDTO user);
    UserResponseDTO toDto(User user);
    List<UserResponseDTO> toListDto(List<User> user);
//  Role
    RoleResponseDTO toDto(Role role);

}
