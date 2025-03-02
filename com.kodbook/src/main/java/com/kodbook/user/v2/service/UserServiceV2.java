package com.kodbook.user.v2.service;

import com.kodbook.entity.User;

public interface UserServiceV2 {
    User findByUsername(String username);

    void updateUser(User user);
}
