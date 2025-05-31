package com.kodbook.auth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class AuthenticationManagerConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager parentAuthenticationManager;

    public AuthenticationManagerConfig(PasswordEncoder passwordEncoder,
                                       @Qualifier("authUserDetailsService") UserDetailsService userDetailsService,
                                       AuthenticationManager parentAuthenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.parentAuthenticationManager = parentAuthenticationManager;
    }


    @Bean(name = "authUserAuthenticationManager")
    public AuthenticationManager authUserAuthenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(List.of(daoAuthenticationProvider), parentAuthenticationManager);
    }


}
