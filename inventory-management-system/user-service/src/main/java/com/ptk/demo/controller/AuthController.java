package com.ptk.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptk.demo.dto.request.ChangePasswordRequest;
import com.ptk.demo.dto.request.LoginRequest;
import com.ptk.demo.dto.request.RegisterRequest;
import com.ptk.demo.dto.response.LoginResponse;
import com.ptk.demo.dto.response.UserResponse;
import com.ptk.demo.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
    name = "Authentication",
    description = "APIs for user authentication"
)
public class AuthController {

    private final AuthService authService;

    // Local HTTP: false
    // Production HTTPS: true
    @Value("${app.cookie.secure:false}")
    private boolean secureCookie;

    // 1. LOGIN
    @Operation(summary = "User Login")
    @ApiResponse(
        responseCode = "200",
        description = "Login successful"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Invalid username or password"
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        // Validate credentials and generate JWT
        LoginResponse loginResponse = authService.login(request);

        String token = loginResponse.getToken();

        // Store JWT in an HttpOnly cookie
        ResponseCookie cookie = ResponseCookie
                .from("access_token", token)
                .httpOnly(true)
                .secure(secureCookie)
                .path("/")
                .sameSite("Lax")
                .maxAge(900)
                .build();

        // Set cookie in response
        return ResponseEntity.ok()
                .header(
                    HttpHeaders.SET_COOKIE,
                    cookie.toString()
                )
                .body(loginResponse);
    }

    // 2. LOGOUT
    @Operation(summary = "User Logout")
    @ApiResponse(
        responseCode = "200",
        description = "Logout successful"
    )
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        authService.logout();

        ResponseCookie clearCookie = ResponseCookie
                .from("access_token", "")
                .httpOnly(true)
                .secure(secureCookie)
                .path("/")
                .sameSite("Lax")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(
                    HttpHeaders.SET_COOKIE,
                    clearCookie.toString()
                )
                .body("Logout successful");
    }



    // 3. CHANGE PASSWORD
    @Operation(summary = "Change Password")
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        authService.changePassword(request);

        return ResponseEntity.ok(
                "Password changed successfully"
        );
    }

    // 4. SETUP ADMIN
    @Operation(summary = "Setup Admin")
    @PostMapping("/setup-admin")
    public ResponseEntity<UserResponse> setupAdmin(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response =
                authService.setupAdmin(request);

        return ResponseEntity.ok(response);
    }
}
