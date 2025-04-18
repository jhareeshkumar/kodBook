package com.kodbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringBootAdminServerConfig {

    private static final String SBA_ADMIN_CONTEXT_PATH = "/sba-server";

    @Bean
    @Order(value = 1)
    public SecurityFilterChain springBootAdminServerSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(SBA_ADMIN_CONTEXT_PATH + "/**")
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(SBA_ADMIN_CONTEXT_PATH + "/instances/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SBA_ADMIN_CONTEXT_PATH + "/assets/**").permitAll()
                        .requestMatchers(SBA_ADMIN_CONTEXT_PATH + "/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage(SBA_ADMIN_CONTEXT_PATH + "/login").permitAll()
                        .defaultSuccessUrl(SBA_ADMIN_CONTEXT_PATH + "/wallboard", true)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl(SBA_ADMIN_CONTEXT_PATH + "/login?expired")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .rememberMe(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl(SBA_ADMIN_CONTEXT_PATH + "/logout").permitAll()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }
}
