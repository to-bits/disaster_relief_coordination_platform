package com.drcp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotBlank
    @Column(nullable = false)
    private String firstName;



    @NotBlank
    @Column(nullable = false)
    private String lastName;



    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;



    @NotBlank
    @Column(nullable = false)
    private String password;



    @Column(unique = true)
    private String phone;



    private String address;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",

            joinColumns = @JoinColumn(
                    name = "user_id"
            ),

            inverseJoinColumns = @JoinColumn(
                    name = "role_id"
            )
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();



    @Builder.Default
    private Boolean enabled = true;



    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();



    private LocalDateTime updatedAt;



    @PreUpdate
    public void updateTimestamp(){

        updatedAt = LocalDateTime.now();

    }

}