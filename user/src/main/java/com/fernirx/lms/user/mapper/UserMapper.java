package com.fernirx.lms.user.mapper;

import com.fernirx.lms.user.dtos.UserDTO;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toListDto(List<User> user);
}
