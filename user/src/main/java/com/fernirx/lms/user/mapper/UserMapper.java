package com.fernirx.lms.user.mapper;


import com.fernirx.lms.infrastructure.security.CustomUserDetails;
import com.fernirx.lms.user.dto.request.UserCreateRequest;
import com.fernirx.lms.user.dto.response.UserResponse;
import com.fernirx.lms.user.entity.Role;
import com.fernirx.lms.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest user);

    @Mapping(source = "role.name", target = "roleName")
    UserResponse toDto(User user);

    @Mapping(target = "authorities", expression = "java(mapAuthorities(user.getRole()))")
    CustomUserDetails toCustomUserDetails(User user);

    default Set<GrantedAuthority> mapAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    List<UserResponse> toListDto(List<User> user);
}
