package com.fernirx.lms.user.mapper;

import com.fernirx.lms.user.dto.response.RoleResponseDTO;
import com.fernirx.lms.user.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponseDTO toDto(Role role);
}
