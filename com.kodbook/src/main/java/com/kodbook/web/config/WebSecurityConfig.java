package com.kodbook.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    @Order(value = 2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {

//	http.csrf(csrf->csrf.disable());
        http.cors(cors -> cors.disable());

        http.securityMatcher("/web/**");// Match all requests except `/api/**`

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/web/login", "/web/openSignUp", "/web/sign-up", "/css/**")
                        .permitAll()
                        .anyRequest().hasRole("USER")
                )
                .formLogin(form -> form
                                .loginPage("/web/login")
//			.loginProcessingUrl("/login")
                                .defaultSuccessUrl("/web/home", true)
                                .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
                        .invalidSessionUrl("/web/login")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/web/login")
                )
                .logout(logout -> logout
                        .logoutUrl("/web/logout")
                        .logoutSuccessUrl("/web/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	return config.getAuthenticationManager();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}