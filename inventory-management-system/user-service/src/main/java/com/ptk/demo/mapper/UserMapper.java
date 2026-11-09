package com.ptk.demo.mapper;

import org.springframework.stereotype.Component;

import com.ptk.demo.dto.request.UserRequest;
import com.ptk.demo.dto.response.UserResponse;
import com.ptk.demo.model.Role;
import com.ptk.demo.model.User;

@Component
public class UserMapper {

    public static User toEntity(UserRequest request, Role role) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(role)
                .mustChangePassword(true)
                .build();
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .mustChangePassword(user.isMustChangePassword())
                .lastLoginAt(user.getLastLoginAt())
                .createdAt(user.getCreatedAt())
                .role(RoleMapper.toResponse(user.getRole()))
                .build();
    }
}