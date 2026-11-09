
package com.ptk.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptk.demo.dto.request.ChangePasswordRequest;
import com.ptk.demo.dto.request.LoginRequest;
import com.ptk.demo.dto.request.RegisterRequest;
import com.ptk.demo.dto.response.LoginResponse;
import com.ptk.demo.dto.response.RoleResponse;
import com.ptk.demo.dto.response.UserResponse;
import com.ptk.demo.exception.AuthenticationException;
import com.ptk.demo.model.Role;
import com.ptk.demo.model.User;
import com.ptk.demo.repository.RoleRepository;
import com.ptk.demo.repository.UserRepository;
import com.ptk.demo.service.AuthService;
import com.ptk.demo.service.JwtService;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // LOGIN
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new AuthenticationException(
                                "Invalid username or password"
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new AuthenticationException(
                    "Invalid username or password"
            );
        }

        if (user.getRole() == null) {
            throw new AuthenticationException(
                    "User role not found"
            );
        }

        if (user.getStatus() == null
                || !user.getStatus().name().equals("ACTIVE")) {

            throw new AuthenticationException(
                    "User account is not active"
            );
        }

        user.setLastLoginAt(LocalDateTime.now());

        String role = user.getRole().getName();

        String token = jwtService.generateToken(
                user.getUsername(),
                role
        );

        return new LoginResponse(
                token,
                user.getUsername(),
                user.isMustChangePassword(),
                List.of(role)
        );
    }

    // SETUP ADMIN
    @Override
    public UserResponse setupAdmin(RegisterRequest request) {

        if (userRepository.existsByRole_Name("ADMIN")) {
            throw new AuthenticationException(
                    "Admin already exists"
            );
        }

        if (userRepository
                .findByUsername(request.getUsername())
                .isPresent()) {

            throw new AuthenticationException(
                    "Username already exists"
            );
        }

        Role adminRole = roleRepository
                .findByName("ADMIN")
                .orElseThrow(() ->
                        new AuthenticationException(
                                "ADMIN role not found"
                        )
                );

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(adminRole);

        // Admin sets their own password during initial setup.
        user.setMustChangePassword(false);

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getStatus(),
                savedUser.isMustChangePassword(),
                savedUser.getLastLoginAt(),
                savedUser.getCreatedAt(),
                new RoleResponse(
                        savedUser.getRole().getId(),
                        savedUser.getRole().getName()
                )
        );
    }

    // CHANGE PASSWORD
    @Override
    public void changePassword(ChangePasswordRequest request) {

        String username = jwtService.getCurrentUsername();

        if (username == null || username.isBlank()) {
            throw new AuthenticationException(
                    "User is not authenticated"
            );
        }

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new AuthenticationException(
                                "User not found"
                        )
                );

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new AuthenticationException(
                    "Current password is incorrect"
            );
        }

        if (!request.getNewPassword().equals(
                request.getConfirmPassword())) {

            throw new AuthenticationException(
                    "New password and confirm password do not match"
            );
        }

        if (passwordEncoder.matches(
                request.getNewPassword(),
                user.getPassword())) {

            throw new AuthenticationException(
                    "New password must be different from current password"
            );
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        // Password changed successfully.
        user.setMustChangePassword(false);
    }
    
    @Override
    public void logout() {
        // No server-side session to invalidate because JWT is stateless.
        // The controller clears the access_token cookie.
    }
}
