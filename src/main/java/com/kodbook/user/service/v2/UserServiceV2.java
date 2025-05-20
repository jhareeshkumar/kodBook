package com.kodbook.user.service.v2;

import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;

import java.util.List;

public interface UserServiceV2 {
    User findByUsername(String username);

    void updateUser(User user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
}
