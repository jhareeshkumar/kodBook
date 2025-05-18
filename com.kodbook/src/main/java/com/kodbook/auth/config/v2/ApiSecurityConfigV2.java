package com.kodbook.auth.config.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfigV2 {
    private static final String API_PREFIX_V2 = "/api/v2";
    private static final String[] whitelistUrls = {API_PREFIX_V2 + "/auth/login"};

    @Bean
    @Order(value = 3)
    SecurityFilterChain apiSecurityFilterChainV2(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(API_PREFIX_V2 + "/**")
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(whitelistUrls)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
