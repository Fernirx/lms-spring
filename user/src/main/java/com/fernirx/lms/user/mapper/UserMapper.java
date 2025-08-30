package com.fernirx.lms.user.mapper;


import com.fernirx.lms.user.dto.request.UserCreateDTO;
import com.fernirx.lms.user.dto.response.UserResponseDTO;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateDTO user);

    UserResponseDTO toDto(User user);

    List<UserResponseDTO> toListDto(List<User> user);
}
