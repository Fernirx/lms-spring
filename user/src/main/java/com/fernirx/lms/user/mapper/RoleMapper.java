package com.fernirx.lms.user.mapper;

import com.fernirx.lms.user.dto.response.RoleResponse;
import com.fernirx.lms.user.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toDto(Role role);
}
