package com.drcp.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {


    @NotBlank(message = "First name is required")
    private String firstName;



    @NotBlank(message = "Last name is required")
    private String lastName;



    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;



    @NotBlank(message = "Password is required")
    @jakarta.validation.constraints.Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;



    private String phone;


    private String address;

}