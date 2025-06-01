package com.kodbook.authservice.security;

import com.kodbook.authservice.entity.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {

    private AuthUser authUser;

    public AuthUserDetails(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authUser.getRole().name()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return authUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return authUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return authUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return authUser.isEnabled();
    }
}
