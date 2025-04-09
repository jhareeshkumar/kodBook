package com.kodbook.web.service;

import com.kodbook.auth.api.v1.dto.ChangePasswordRequest;
import com.kodbook.exception.custom.IncorrectPasswordException;
import com.kodbook.exception.custom.SamePasswordException;
import com.kodbook.user.entity.User;
import com.kodbook.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userExists(String userName, String email) {
        Optional<User> user1 = userRepository.findByUserName(userName);
        Optional<User> user2 = userRepository.findByEmail(email);
        if (user1.isPresent()) {
            return true;
        } else if (user2.isPresent()) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean validateUser(String userName, String password) {
        Optional<User> user = userRepository.findByUserName(userName);
//		String dbPass = null;
//		if (user!=null) {
//		    dbPass = user.getPassword();
//		}

        if (user != null && password.equals(user.get().getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String userName) {
        return userRepository.findByUserName(userName).orElseThrow();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    //    @Override // when spring security not involved
//    public boolean authenticateUser(String userNameOrEmail, String password) {
//	
//	Optional<User> optional = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
//	if (optional.isPresent() && optional.get().getPassword().equals(password)) {
//	    return true;
//	}
//	return false;
//    }
    @Override // when spring secuirty is involved
    @Transactional(readOnly = true)
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
        Optional<User> optional = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail);
        if (optional.isEmpty()) {
            return null;
        } else {

            return optional.get();
        }
    }

    @Override
    public void changePassword(String username, ChangePasswordRequest request) {

        Optional<User> optional = userRepository.findByUserName(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        User user = optional.get();
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
