package com.drcp.service.impl;

import com.drcp.dto.request.LoginRequest;
import com.drcp.dto.request.RefreshTokenRequest;
import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.JwtAuthResponse;
import com.drcp.dto.response.LoginResponse;
import com.drcp.dto.response.UserResponse;
import com.drcp.entity.Role;
import com.drcp.entity.User;
import com.drcp.exception.BadRequestException;
import com.drcp.exception.DuplicateResourceException;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.RoleRepository;
import com.drcp.repository.UserRepository;
import com.drcp.security.CustomUserDetails;
import com.drcp.security.JwtTokenProvider;
import com.drcp.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Phone already exists");
        }

        Role citizenRole = roleRepository.findByName("ROLE_CITIZEN")
                .orElseThrow(() -> new ResourceNotFoundException("Default role ROLE_CITIZEN not found"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .roles(Set.of(citizenRole))
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        return LoginResponse.builder()
                .id(userDetails.getId())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getUsername())
                .roles(userDetails.getRoles())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .message("Login successful")
                .build();
    }

    @Override
    public JwtAuthResponse refreshToken(RefreshTokenRequest request) {
        String token = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(token)) {
            throw new BadRequestException("Invalid or expired refresh token");
        }

        String email = jwtTokenProvider.getUsernameFromJwt(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String newAccessToken = jwtTokenProvider.generateAccessToken(email, user.getId(), roles);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email);

        return JwtAuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(
                        user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}