package com.kodbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entity.User;
import com.kodbook.service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute User user) {
	// TODO: process POST request
	boolean signUpStatus = userService.userExists(user.getUserName(), user.getEmail());
	if (signUpStatus == false) {
	    userService.addUser(user);
	    System.out.println("User Added Successfully");
	}
	System.out.println("User already Exists with same details");
	return "index";
    }
    @PostMapping("/login")
    public String login(@RequestParam String userNameOrEmail, @RequestParam String password) {
        //TODO: process POST request
        boolean loginStatus = userService.validateUser(userNameOrEmail,password);
        if (loginStatus) {
            System.out.println("Login SuccessFull");
	    return "home";
	}
        System.out.println("Invalid Credentials Please Login");
        return "index";
    }
    
}
