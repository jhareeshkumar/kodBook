package com.kodbook.user.service.v2.impl;

import com.kodbook.exception.custom.UsernameNotFoundException;
import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import com.kodbook.user.exception.UserIdNotFoundException;
import com.kodbook.user.mapper.UserMapper;
import com.kodbook.user.repository.UserRepository;
import com.kodbook.user.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceV2Impl implements UserServiceV2 {

    private final UserMapper userMapper;
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

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("User not found with id : " + id));
        return userMapper.mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        return userMapper.toUserDTOList(all);
    }
}
