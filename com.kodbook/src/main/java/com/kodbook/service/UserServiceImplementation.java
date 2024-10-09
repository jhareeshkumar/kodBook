package com.kodbook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entity.User;
import com.kodbook.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userExists(String userName, String email) {
	// TODO Auto-generated method stub
	Optional<User> byUserName = userRepository.findByUserName(userName);
	Optional<User> byEmail = userRepository.findByEmail(email);
	
	if (byUserName.isPresent() || byEmail.isPresent()) {
	    return true;
	}
	return false;
    }

    @Override
    public void addUser(User user) {
	// TODO Auto-generated method stub
	userRepository.save(user);
	
    }

    @Override
    public boolean validateUser(String userNameOrEmail, String password) {
	// TODO Auto-generated method stub
	String dbPassword = userRepository.findByUserNameOrEmail(userNameOrEmail,userNameOrEmail).get().getPassword();
	if (dbPassword.equals(password)) {
	    return true;
	}
	return false;
    }
}
