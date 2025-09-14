package com.fernirx.lms.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final boolean isDeleted;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}
