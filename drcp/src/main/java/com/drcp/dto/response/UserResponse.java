package com.drcp.dto.response;


import lombok.*;

import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {


    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    private String phone;


    private Set<String> roles;

}