package com.kodbook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodbook.entity.User;
import com.kodbook.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userExists(String userName, String email) {
	// TODO Auto-generated method stub
	User user1 = userRepository.findByUserName(userName);
	User user2 = userRepository.findByEmail(email);
	if (user1 != null || user2 != null) {
	    return true;
	}
	return false;
    }

    @Override
    public boolean validateUser(String userName, String password) {
	// TODO Auto-generated method stub
	User user = userRepository.findByUserName(userName);
//		String dbPass = null;
//		if (user!=null) {
//		    dbPass = user.getPassword();
//		}
	if (user != null && password.equals(user.getPassword())) {
	    return true;
	}
	return false;
    }

    @Override
    public User getUser(String userName) {
	// TODO Auto-generated method stub
	return userRepository.findByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
	// TODO Auto-generated method stub
	userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
	// TODO Auto-generated method stub
	String encodedPassword = passwordEncoder.encode(user.getPassword());
	user.setPassword(encodedPassword);
	userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String userNameOrEmail, String password) {
	// TODO Auto-generated method stub
	
	Optional<User> optional = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
	if (optional.isPresent() && optional.get().getPassword().equals(password)) {
	    return true;
	}
	return false;
    }

    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail) {
	// TODO Auto-generated method stub
	Optional<User> optional = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail);
	if (optional.isEmpty()) {
	    return null;
	} else {
	    User user = optional.get();
	    return user;
	}
    }
    
    
    
    
    
}
