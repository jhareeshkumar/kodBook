package com.kodbook.authservice.security;

import com.kodbook.authservice.entity.AuthUser;
import com.kodbook.authservice.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("authUserDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public AuthUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUserOptional = authUserRepository.findByUsername(username);
        if (authUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("No User Exists with username: " + username);
        }
        return new AuthUserDetails(authUserOptional.get());
    }
}
