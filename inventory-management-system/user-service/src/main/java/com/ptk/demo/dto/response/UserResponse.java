package com.ptk.demo.dto.response;

import java.time.LocalDateTime;

import com.ptk.demo.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private Status status;

    private Boolean mustChangePassword;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createdAt;

    private RoleResponse role;
}