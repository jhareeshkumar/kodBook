package com.kodbook.web.service;

import com.kodbook.auth.api.v1.dto.ChangePasswordRequest;
import com.kodbook.user.entity.User;

public interface UserService {

    void addUser(User user);

    boolean userExists(String userName, String email);

    boolean validateUser(String userName, String password);

    User getUser(String userName);

    void updateUser(User user);

    boolean authenticateUser(String userNameOrEmail, String password);

    User getUserByUsernameOrEmail(String usernameOrEmail);

    void changePassword(String username, ChangePasswordRequest request);
}
