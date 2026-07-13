package com.drcp.security;


import lombok.RequiredArgsConstructor;
<<<<<<< HEAD


import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


>>>>>>> feature-backend
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


<<<<<<< HEAD
    private final CustomUserDetailsService userDetailsService;
=======
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

>>>>>>> feature-backend




    @Bean
    public SecurityFilterChain securityFilterChain(
<<<<<<< HEAD
            HttpSecurity http
    ) throws Exception {
=======
            HttpSecurity http,
            CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {

>>>>>>> feature-backend


        http

<<<<<<< HEAD
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
=======
                .csrf(csrf ->
                        csrf.disable()
                )


                .authorizeHttpRequests(auth ->
                        auth

                                .requestMatchers(
                                        "/",
                                        "/login",
                                        "/register",
                                        "/api/auth/register",
                                        "/css/**",
                                        "/js/**"
                                )
                                .permitAll()

                                .requestMatchers("/admin/**")
                                .hasRole("ADMIN")


                                .requestMatchers("/citizen/**")
                                .hasRole("CITIZEN")


                                .requestMatchers("/volunteer/**")
                                .hasRole("VOLUNTEER")


                                .requestMatchers("/donor/**")
                                .hasRole("DONOR")


                                .anyRequest()
                                .authenticated()
>>>>>>> feature-backend

                )


<<<<<<< HEAD
                .formLogin(login -> login

                        .loginPage("/login")

                        .defaultSuccessUrl("/dashboard")

                        .permitAll()
=======

                .formLogin(form ->
                        form

                                .loginPage("/login")

                                .successHandler(customAuthenticationSuccessHandler)

                                .permitAll()
>>>>>>> feature-backend

                )


<<<<<<< HEAD
                .logout(logout -> logout

                        .logoutSuccessUrl("/")
=======

                .logout(logout ->
                        logout

                                .logoutSuccessUrl("/")
>>>>>>> feature-backend

                );



        return http.build();

    }




<<<<<<< HEAD
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
=======


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {


        return configuration
                .getAuthenticationManager();
>>>>>>> feature-backend

    }

}