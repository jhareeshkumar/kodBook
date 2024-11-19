package com.kodbook.service;

import com.kodbook.entity.User;

public interface UserService {

    void addUser(User user);

    boolean userExists(String userName, String email);

    boolean validateUser(String userName, String password);

    User getUser(String userName);

    void updateUser(User user);
    
    boolean authenticateUser(String userNameOrEmail,String password);
    
   User getUserByUsernameOrEmail(String usernameOrEmail);
}
