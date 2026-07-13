package com.drcp.service.impl;


<<<<<<< HEAD
import com.drcp.dto.RegisterRequest;
=======
import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.UserResponse;
import com.drcp.entity.Role;
>>>>>>> feature-backend
import com.drcp.entity.User;
import com.drcp.repository.RoleRepository;
import com.drcp.repository.UserRepository;
import com.drcp.service.interfaces.UserService;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======

import lombok.RequiredArgsConstructor;


>>>>>>> feature-backend
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
=======
import java.util.Set;

>>>>>>> feature-backend


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


<<<<<<< HEAD
    private final UserRepository userRepository;

=======

    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


>>>>>>> feature-backend
    private final PasswordEncoder passwordEncoder;



<<<<<<< HEAD
=======

>>>>>>> feature-backend
    @Override
    public UserResponse registerUser(RegisterRequest request) {


        // Email duplicate check

        if(userRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException(
                    "Email already exists"
            );

        }



        // Phone duplicate check

        if(request.getPhone()!=null &&
                userRepository.existsByPhone(request.getPhone())){


            throw new RuntimeException(
                    "Phone already exists"
            );

        }




        // Find default role

        Role citizenRole = roleRepository
                .findByName("ROLE_CITIZEN")
                .orElseThrow(
                        () -> new RuntimeException(
                                "Default role not found"
                        )
                );




        // Create user



        User user = User.builder()

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .password(
<<<<<<< HEAD
                        passwordEncoder.encode(request.getPassword())
=======
                        passwordEncoder.encode(
                                request.getPassword()
                        )
>>>>>>> feature-backend
                )

                .phone(request.getPhone())

                .address(request.getAddress())

<<<<<<< HEAD
                .role(request.getRole())
=======
                .roles(Set.of(citizenRole))
>>>>>>> feature-backend

                .build();



<<<<<<< HEAD
        return userRepository.save(user);
=======

        User savedUser =
                userRepository.save(user);



        return mapToResponse(savedUser);

>>>>>>> feature-backend
    }



<<<<<<< HEAD

    @Override
    public List<User> getAllUsers(){

        return userRepository.findAll();

    }




    @Override
    public Optional<User> getUserByEmail(String email){

        return userRepository.findByEmail(email);

    }
=======



    private UserResponse mapToResponse(User user){


        return UserResponse.builder()

                .id(user.getId())

                .firstName(user.getFirstName())

                .lastName(user.getLastName())

                .email(user.getEmail())

                .phone(user.getPhone())

                .roles(
                        user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(
                                        java.util.stream.Collectors.toSet()
                                )
                )

                .build();


    }

>>>>>>> feature-backend

}