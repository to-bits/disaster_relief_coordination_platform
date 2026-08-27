package com.drcp.controller.auth;

import com.drcp.dto.request.LoginRequest;
import com.drcp.dto.request.RefreshTokenRequest;
import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.JwtAuthResponse;
import com.drcp.dto.response.LoginResponse;
import com.drcp.dto.response.UserResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "User registered successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.loginUser(request);

        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        JwtAuthResponse response = userService.refreshToken(request);

        return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader != null && !authHeader.isBlank()) {
            userService.logout(authHeader);
        }

        return ResponseEntity.ok(ApiResponse.success("Logged out successfully", "Token revoked"));
    }
}