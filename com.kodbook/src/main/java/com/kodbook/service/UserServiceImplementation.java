package com.kodbook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodbook.dto.ChangePasswordRequest;
import com.kodbook.entity.User;
import com.kodbook.exception.IncorrectPasswordException;
import com.kodbook.exception.SamePasswordException;
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

//    @Override // when spring security not involved
//    public boolean authenticateUser(String userNameOrEmail, String password) {
//	// TODO Auto-generated method stub
//	
//	Optional<User> optional = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
//	if (optional.isPresent() && optional.get().getPassword().equals(password)) {
//	    return true;
//	}
//	return false;
//    }
    @Override // when spring secuirty is involved
    public boolean authenticateUser(String userNameOrEmail, String password) {
	// TODO Auto-generated method stub

	Optional<User> optional = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

	if (optional.isPresent()) {
	    String dbEncodedPassword = optional.get().getPassword();
	    boolean isPasswordMatches = passwordEncoder.matches(password, dbEncodedPassword);
	    if (isPasswordMatches) {
		System.out.println(
			"Valid User" + "Password : " + dbEncodedPassword + "matches" + isPasswordMatches + "present");
		return true;
	    }
	}
	System.out.println("Invalid User");
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

    @Override
    public void changePasssword(String username, ChangePasswordRequest request) {
	// TODO Auto-generated method stub

	User user = userRepository.findByUserName(username);

	if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
	    throw new IncorrectPasswordException("Current Password is Incorrect");
	}
	
	if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
	    throw new SamePasswordException("New Password cannot be the same as the Current Password.");
	}
	user.setPassword(passwordEncoder.encode(request.getNewPassword()));
	userRepository.save(user);
    }

}
