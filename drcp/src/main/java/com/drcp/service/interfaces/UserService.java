package com.drcp.service.interfaces;


import com.drcp.dto.request.LoginRequest;
import com.drcp.dto.request.RefreshTokenRequest;
import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.JwtAuthResponse;
import com.drcp.dto.response.LoginResponse;
import com.drcp.dto.response.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    LoginResponse loginUser(LoginRequest request);

    JwtAuthResponse refreshToken(RefreshTokenRequest request);

    void logout(String token);
}