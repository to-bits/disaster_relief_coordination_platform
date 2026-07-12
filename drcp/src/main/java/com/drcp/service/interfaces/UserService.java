package com.drcp.service.interfaces;

import com.drcp.dto.RegisterRequest;
import com.drcp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(RegisterRequest request);

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);
}