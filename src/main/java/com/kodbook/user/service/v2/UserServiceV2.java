package com.kodbook.user.service.v2;

import com.kodbook.user.entity.User;

public interface UserServiceV2 {
    User findByUsername(String username);

    void updateUser(User user);
}
