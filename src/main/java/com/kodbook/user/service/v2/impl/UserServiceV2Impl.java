package com.kodbook.user.service.v2.impl;

import com.kodbook.exception.custom.UsernameNotFoundException;
import com.kodbook.user.entity.User;
import com.kodbook.user.repository.UserRepository;
import com.kodbook.user.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
