package com.kodbook.service;

import com.kodbook.entity.User;

public interface UserService {

    boolean userExists(String userName, String email);

    void addUser(User user);

    boolean validateUser(String userName, String password);

}
