package com.fernirx.lms.user.service;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.ResourceNotFoundException;
import com.fernirx.lms.user.entity.Role;
import com.fernirx.lms.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.ROLE_NOT_FOUND));
    }
}
