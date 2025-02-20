package com.kodbook.user.v2.service.impl;

import org.springframework.stereotype.Service;

import com.kodbook.entity.User;
import com.kodbook.exception.custom.UsernameNotFoundException;
import com.kodbook.repository.UserRepository;
import com.kodbook.user.v2.service.UserServiceV2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceV2Impl implements UserServiceV2 {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
	return userRepository.findByUserName(username)
		.orElseThrow(() -> new UsernameNotFoundException("Username not found:" + username));
    }

    @Override
    public void updateUser(User user) {
	userRepository.save(user);
    }

}
