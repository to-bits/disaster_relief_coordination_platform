package com.drcp.service.interfaces;


import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.UserResponse;


public interface UserService {


    UserResponse registerUser(RegisterRequest request);


}