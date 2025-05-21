package com.kodbook.user.service.v2.impl;

import com.kodbook.exception.custom.UsernameNotFoundException;
import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import com.kodbook.user.exception.UserIdNotFoundException;
import com.kodbook.user.mapper.UserMapper;
import com.kodbook.user.repository.UserRepository;
import com.kodbook.user.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<UserDto> getUsers(int pageNumber, int pageSize, String sortDirection, String[] sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection), sortBy);
        return userRepository.findAll(pageable).map(userMapper::mapToDto);
    }
}
