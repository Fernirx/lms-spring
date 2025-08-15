package com.fernirx.lms.user.mapper;

import com.fernirx.lms.user.dtos.response.UserResponseDTO;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDTO toDto(User user);
    User toEntity(UserResponseDTO userDTO);
    List<UserResponseDTO> toListDto(List<User> user);
}
