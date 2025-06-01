package com.kodbook.userservice.security.internal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class InternalApiSecurityConfig {

    @Bean
    public SecurityFilterChain internalApiSecurityFilterChain(HttpSecurity http, @Qualifier("internalAuthenticationManger") AuthenticationManager authenticationManager) throws Exception {
        return http
                .securityMatcher("/internal/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/internal/api/v3/users/**").hasRole("AUTH_SERVICE_CLIENT")
                        .anyRequest().denyAll()
                )
                .authenticationManager(authenticationManager)
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
