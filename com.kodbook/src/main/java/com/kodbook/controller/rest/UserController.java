package com.kodbook.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodbook.dto.ChangePasswordRequest;
import com.kodbook.dto.ErrorDto;
import com.kodbook.dto.LoginRequest;
import com.kodbook.dto.SignupRequest;
import com.kodbook.dto.SuccessResponse;
import com.kodbook.dto.UserDto;
import com.kodbook.entity.User;
import com.kodbook.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*",allowCredentials = "true")
@RestController(value = "restUserController")
@RequestMapping("/api/v1/user")
@Tag(name = "User Management",description = "APIs for User Management")
public class UserController {

    private final UserService userService;
    
    
    public UserController(UserService userService) {
	this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
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
	System.out.println("login invoked" + loginRequest);
	boolean validateUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
	if (validateUser) {
	    UserDto userDto = new UserDto();
	    userDto.setusername(loginRequest.getUsername());
	    SuccessResponse successResponse = new SuccessResponse("Login Success", userDto);
	    return new ResponseEntity<>(successResponse, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(new ErrorDto("Invalid Credentials", HttpStatus.UNAUTHORIZED.value(),
		    HttpStatus.UNAUTHORIZED.getReasonPhrase()), HttpStatus.UNAUTHORIZED);
	}
    }
    
    @Operation(summary = "Get User Profile", description = "Get User Profile by username")
    @ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile",
	                 content = @Content(mediaType = "application/json",
	                 schema = @Schema(implementation = SuccessResponse.class))),
	    @ApiResponse(responseCode = "404", description = "User not found")
	})
    @GetMapping(value = "/profile/{username}")
    public ResponseEntity<?> getProfile(@Parameter(description = "Username of the user", example = "john") @PathVariable String username) {
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
	
	userService.changePassword(userDetails.getUsername(), request);
	
	return new ResponseEntity<>(new SuccessResponse("Password Changed Successfully."), HttpStatus.OK);
    }
}
