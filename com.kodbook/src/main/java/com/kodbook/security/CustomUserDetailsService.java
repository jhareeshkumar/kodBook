package com.kodbook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kodbook.entity.User;
import com.kodbook.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	User user = userRepository.findByUserName(username);
	
	if (user==null) {
	    throw new UsernameNotFoundException(username+"No User Exists");
	}
	
	return new CustomUserDetails(user);

    }
}
