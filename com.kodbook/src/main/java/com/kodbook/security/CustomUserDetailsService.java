package com.kodbook.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kodbook.entity.User;
import com.kodbook.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }



    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<User> optional = userRepository.findByUserName(username);
	
	if (optional.isEmpty()) {
	    throw new UsernameNotFoundException(username+"No User Exists");
	}
	
	return new CustomUserDetails(optional.get());

    }
}
