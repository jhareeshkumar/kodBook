package com.kodbook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodbook.dto.ErrorDto;
import com.kodbook.dto.LoginRequest;
import com.kodbook.dto.UserDto;
import com.kodbook.entity.User;
import com.kodbook.service.UserService;

@CrossOrigin("*")
@RestController(value = "RestUserController")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
	super();
	this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
	// TODO: process POST request
	System.out.println(userDto.toString());

	User user = new User();
	user.setUserName(userDto.getUserName());
	user.setEmail(userDto.getEmail());
	user.setPassword(userDto.getPassword());
	user.setDob(userDto.getDob());
	user.setGender(userDto.getGender());

	if (userService.userExists(userDto.getUserName(), userDto.getEmail())) {
	    return new ResponseEntity<>(new ErrorDto("User Already Exists!"), HttpStatus.BAD_REQUEST);
	}
	userService.addUser(user);
	return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	// TODO: process POST requestŚ
	boolean isValidateUser = userService.validateUser(loginRequest.getUserName(), loginRequest.getPassword());
	if (isValidateUser) {
	    return ResponseEntity.status(HttpStatus.OK).body("Login Success");
	} else {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	}
    }

}
