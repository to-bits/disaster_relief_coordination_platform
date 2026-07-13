package com.drcp.controller.auth;

import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.UserResponse;
import com.drcp.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid
            @RequestBody RegisterRequest request
    ){

        UserResponse response =
                userService.registerUser(request);

        return ResponseEntity.ok(response);

    }

}