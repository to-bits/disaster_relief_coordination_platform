package com.drcp.controller.auth;


import com.drcp.dto.request.LoginRequest;
import com.drcp.dto.request.RegisterRequest;
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

        ApiResponse<UserResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "User registered successfully",
                        response
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {


        LoginResponse response =
                userService.loginUser(request);


        ApiResponse<LoginResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Login successful",
                        response
                );


        return ResponseEntity.ok(apiResponse);

    }

}