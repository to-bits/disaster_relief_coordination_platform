package com.drcp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomUserDetailsService customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http

                .authenticationProvider(authenticationProvider())

                .csrf(csrf ->
                        csrf.disable()
                )


                .authorizeHttpRequests(auth -> auth


                        .requestMatchers(
                                "/",
                                "/login",
                                "/api/auth/**",
                                "/api/disasters/**",
                                "/api/relief-requests/**",
                                "/api/volunteers/**",
                                "/api/donations/**",
                                "/api/resources/**",
                                "/register",
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

                )


                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                )


                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/")
                );



        return http.build();

    }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();

    }

}