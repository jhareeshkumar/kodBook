package com.kodbook.user.controller.v1;

import com.kodbook.auth.api.v1.dto.ChangePasswordRequest;
import com.kodbook.dto.ErrorDto;
import com.kodbook.dto.request.LoginRequest;
import com.kodbook.dto.request.SignupRequest;
import com.kodbook.dto.response.SuccessResponse;
import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import com.kodbook.web.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController(value = "restUserController")
@RequestMapping("/api/v1/user")
@Tag(name = "User Management", description = "APIs for User Management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        log.info("Signup request received of : {}", signupRequest.getUsername());

        User user = new User();
        user.setUserName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());

        String dateOfBirth = signupRequest.getDateOfBirth().toString();
        user.setDob(dateOfBirth);
        user.setGender(signupRequest.getGender());
        user.setPassword(signupRequest.getPassword());

        if (userService.userExists(signupRequest.getUsername(), signupRequest.getEmail())) {
            log.info("User already exists: {}", signupRequest.getUsername());
            return new ResponseEntity<>(new ErrorDto("User Already Exists!", HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
        }

        userService.addUser(user);
        log.info("User added successfully: {}", user.getUserName());
        return new ResponseEntity<>(new SuccessResponse("Signup Success", signupRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("login request invoked of : {}", loginRequest.getUsername());
        boolean validateUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (validateUser) {
            UserDto userDto = new UserDto();
            userDto.setUsername(loginRequest.getUsername());
            SuccessResponse successResponse = new SuccessResponse("Login Success", userDto);
            log.info("login request processed successfully of : {} ", loginRequest.getUsername());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            log.error("Invalid Credentials of : {}", loginRequest.getUsername());
            return new ResponseEntity<>(new ErrorDto("Invalid Credentials", HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase()), HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Get User Profile", description = "Get User Profile by username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/profile/{username}")
    public ResponseEntity<?> getProfile(
            @Parameter(description = "Username of the user", example = "john") @PathVariable String username) {
        User user = userService.getUserByUsernameOrEmail(username);
        if (user == null) {
            return new ResponseEntity<>(new ErrorDto("user not found with" + username, HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND);
        } else {
            UserDto userDto = new UserDto(user.getId(), user.getUserName(), user.getEmail(), user.getDob(), user.getGender(),
                    user.getCity(), user.getBio(), user.getCollege(), user.getLinkedIn(), user.getGitHub());
            SuccessResponse successResponse = new SuccessResponse("GET profile success", userDto);

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
    }


    @Deprecated(since = "1.0", forRemoval = true)
    @PostMapping("/change-password")
    @Operation(summary = "Change the user's password", description = "Allows an authenticated user to change their password.")

    @ApiResponse(responseCode = "200", description = "Password changed successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, incorrect or same password")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<SuccessResponse> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                                          @RequestBody ChangePasswordRequest request) {

        userService.changePassword(userDetails.getUsername(), request);

        return ResponseEntity.ok(new SuccessResponse("Password Changed Successfully."));
    }

}
