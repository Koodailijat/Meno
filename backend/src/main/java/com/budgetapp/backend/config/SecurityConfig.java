package com.budgetapp.backend.config;

import com.budgetapp.backend.security.JwtAuthenticationFilter;
import com.budgetapp.backend.security.OAuth2LoginSuccessHandler;
import com.budgetapp.backend.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler successHandler;
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
                          OAuth2LoginSuccessHandler successHandler,
                          JwtAuthenticationFilter jwtFilter) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.successHandler = successHandler;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(info ->
                                info.oidcUserService(customOAuth2UserService)
                        )
                        .successHandler(successHandler)
                )
                .build();
    }
}
