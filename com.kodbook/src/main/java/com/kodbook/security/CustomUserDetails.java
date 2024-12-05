package com.kodbook.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kodbook.entity.User;

public class CustomUserDetails implements UserDetails {

    @Autowired
    private User user;

    
    public CustomUserDetails(User user) {
	super();
	this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
	// TODO Auto-generated method stub
	return user.getPassword();
    }

    @Override
    public String getUsername() {
	// TODO Auto-generated method stub
	return user.getUserName();
    }

}
