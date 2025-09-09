package com.fernirx.lms.user.mapper;


import com.fernirx.lms.user.dto.request.UserCreateRequest;
import com.fernirx.lms.user.dto.response.UserResponse;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest user);

    @Mapping(source = "role.name", target = "roleName")
    UserResponse toDto(User user);

    List<UserResponse> toListDto(List<User> user);
}
