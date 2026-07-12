package com.drcp.service.impl;

import com.drcp.dto.RegisterRequest;
import com.drcp.entity.User;
import com.drcp.repository.UserRepository;
import com.drcp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(RegisterRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())   // পরে BCrypt ব্যবহার করব
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(request.getRole())
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}