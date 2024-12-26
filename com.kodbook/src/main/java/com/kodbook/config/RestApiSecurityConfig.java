package com.kodbook.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class RestApiSecurityConfig {

    @Bean
    @Order(value = 1)
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
	 
	 http.securityMatcher("/api/**")  // Match only requests to `/api/**`
	        .csrf(csrf -> csrf.disable())  // Disable CSRF for REST API
	        .cors(cors->cors.configurationSource(apiCorsConfigurationSource()))  // Disable CORS for REST API
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/v1/user/signup").permitAll()  // Allow unauthenticated access to login and signup
	            .anyRequest().authenticated()  // Require authentication for other requests
	        )
	        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .httpBasic();  // Use HTTP Basic for authentication
	    return http.build();
    }
    
    @Bean
    public UrlBasedCorsConfigurationSource apiCorsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://kod-book.vercel.app"));
	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/api/**", configuration);
	return source;
    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//    }
}
