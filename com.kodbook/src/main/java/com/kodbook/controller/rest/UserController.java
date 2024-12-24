package com.kodbook.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodbook.dto.ChangePasswordRequest;
import com.kodbook.dto.ErrorDto;
import com.kodbook.dto.LoginRequest;
import com.kodbook.dto.SignupRequest;
import com.kodbook.dto.SuccessResponse;
import com.kodbook.dto.UserDto;
import com.kodbook.entity.User;
import com.kodbook.service.UserService;

//
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*",allowCredentials = "true")
@RestController(value = "RestUserController")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
	// TODO: process POST request
	System.out.println(signupRequest.toString());

	User user = new User();
	user.setUserName(signupRequest.getUsername());
	user.setEmail(signupRequest.getEmail());

	String dateOfBirth = signupRequest.getDateOfBirth().toString();
	user.setDob(dateOfBirth);
	user.setGender(signupRequest.getGender());
	user.setPassword(signupRequest.getPassword());

	if (userService.userExists(signupRequest.getUsername(), signupRequest.getEmail())) {
	    return new ResponseEntity<>(new ErrorDto("User Already Exists!", HttpStatus.BAD_REQUEST.value(),
		    HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
	}
	userService.addUser(user);
	return new ResponseEntity<>(new SuccessResponse("Signup Success", signupRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	// TODO: process POST request
	System.out.println("login invoked" + loginRequest);
	boolean validateUser = userService.authenticateUser(loginRequest.getusername(), loginRequest.getPassword());
	if (validateUser) {
	    UserDto userDto = new UserDto();
	    userDto.setusername(loginRequest.getusername());
	    SuccessResponse successResponse = new SuccessResponse("Login Success", userDto);
	    return new ResponseEntity<>(successResponse, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(new ErrorDto("Invalid Credentials", HttpStatus.UNAUTHORIZED.value(),
		    HttpStatus.UNAUTHORIZED.getReasonPhrase()), HttpStatus.UNAUTHORIZED);
	}
    }

    @GetMapping(value = "/profile/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {
	User user = userService.getUserByUsernameOrEmail(username);
	if (user == null) {
	    return new ResponseEntity<>(new ErrorDto("user not found with" + username, HttpStatus.NOT_FOUND.value(),
		    HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND);
	} else {
	    UserDto userDto = new UserDto(user.getUserName(), user.getEmail(), user.getDob(), user.getGender(),
		    user.getCity(), user.getBio(), user.getCollege(), user.getLinkedIn(), user.getGitHub());
	    SuccessResponse successResponse = new SuccessResponse("GET profile success", userDto);

	    return new ResponseEntity<>(successResponse, HttpStatus.OK);
	}
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
	    @RequestBody ChangePasswordRequest request) {
	// TODO: process POST request
	
	userService.changePasssword(userDetails.getUsername(), request);
	
	return new ResponseEntity<>(new SuccessResponse("Password Changed Successfully."), HttpStatus.OK);
    }
}
