package com.ptk.demo.mapper;

import org.springframework.stereotype.Component;

import com.ptk.demo.dto.response.RoleResponse;
import com.ptk.demo.model.Role;

@Component
public class RoleMapper {

    public static RoleResponse toResponse(Role role) {

        if (role == null) {
            return null;
        }

        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}