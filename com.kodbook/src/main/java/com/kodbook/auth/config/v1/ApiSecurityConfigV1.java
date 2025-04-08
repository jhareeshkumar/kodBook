package com.kodbook.auth.config.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class ApiSecurityConfigV1 {

    private static final String API_PREFIX_V1 = "/api/v1";
    private static final String[] whitelistUrls = {API_PREFIX_V1 + "/user/signup", API_PREFIX_V1 + "/user/login"};
    
    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    @Bean
    @Order(value = 1)
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(API_PREFIX_V1 + "/**")
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(apiCorsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whitelistUrls).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    UrlBasedCorsConfigurationSource apiCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(frontendUrl));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
