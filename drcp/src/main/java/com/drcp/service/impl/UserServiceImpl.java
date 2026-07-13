package com.drcp.service.impl;


import com.drcp.dto.request.RegisterRequest;
import com.drcp.dto.response.UserResponse;
import com.drcp.entity.Role;
import com.drcp.entity.User;
import com.drcp.repository.RoleRepository;
import com.drcp.repository.UserRepository;
import com.drcp.service.interfaces.UserService;


import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;




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
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .phone(request.getPhone())

                .address(request.getAddress())

                .roles(Set.of(citizenRole))

                .build();




        User savedUser =
                userRepository.save(user);



        return mapToResponse(savedUser);

    }






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


}