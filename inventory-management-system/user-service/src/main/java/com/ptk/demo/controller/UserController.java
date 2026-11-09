package com.ptk.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ptk.demo.dto.request.AssignRoleRequest;
import com.ptk.demo.dto.request.UserRequest;
import com.ptk.demo.dto.response.UserResponse;
import com.ptk.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(
    name = "User Management",
    description = "APIs for managing users and roles"
)
@SecurityRequirement(name = "BearerAuth")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Create User",
        description = "Create a new user in the system"
    )
    @ApiResponse(
        responseCode = "201",
        description = "User created successfully"
    )
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
        summary = "Get All Users",
        description = "Retrieve all users from the system"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Users retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @Operation(
        summary = "Get User By ID",
        description = "Retrieve a user using user ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "User found"
    )
    @ApiResponse(
        responseCode = "404",
        description = "User not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "User ID")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    @Operation(
        summary = "Update User",
        description = "Update existing user information"
    )
    @ApiResponse(
        responseCode = "200",
        description = "User updated successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "User not found"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "User ID")
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok(
                userService.updateUser(id, request)
        );
    }

    @Operation(
        summary = "Assign Role",
        description = "Assign a role to a user"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Role assigned successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "User or Role not found"
    )
    @PutMapping("/{userId}/role")
    public ResponseEntity<Void> assignRole(
            @Parameter(description = "User ID")
            @PathVariable Long userId,
            @Valid @RequestBody AssignRoleRequest request) {

        userService.assignRole(userId, request);

        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Delete User",
        description = "Delete a user by ID"
    )
    @ApiResponse(
        responseCode = "204",
        description = "User deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "User not found"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID")
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Get User By Username",
        description = "Retrieve a user using username"
    )
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserByUsername(username)
        );
    }
}