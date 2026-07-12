package com.drcp.security;


import lombok.RequiredArgsConstructor;


import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;




    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http

                .csrf(csrf -> csrf.disable())


                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/register",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()


                        .anyRequest()
                        .authenticated()

                )


                .formLogin(login -> login

                        .loginPage("/login")

                        .defaultSuccessUrl("/dashboard")

                        .permitAll()

                )


                .logout(logout -> logout

                        .logoutSuccessUrl("/")

                );



        return http.build();

    }




    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

}