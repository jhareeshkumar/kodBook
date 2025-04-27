package com.kodbook.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private static final String LOGIN_PAGE_URL = "/web/login";

    @Bean
    @Order(value = 4)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/web/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(LOGIN_PAGE_URL, "/web/openSignUp", "/web/sign-up", "/css/**")
                        .permitAll()
                        .requestMatchers("/web/admin/**").hasRole("ADMIN")
                        .requestMatchers("/web/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(LOGIN_PAGE_URL)
                        .defaultSuccessUrl("/web/home", true)
                        .failureUrl(LOGIN_PAGE_URL + "?error")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl(LOGIN_PAGE_URL)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl(LOGIN_PAGE_URL + "?expired")
                )
                .logout(logout -> logout
                        .logoutUrl("/web/logout")
                        .logoutSuccessUrl(LOGIN_PAGE_URL + "?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}