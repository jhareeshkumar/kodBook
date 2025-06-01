package com.kodbook.userservice.profile.api.internal.impl;

import com.kodbook.userservice.profile.api.internal.UserProfileInternalApi;
import com.kodbook.userservice.profile.dto.ApiResponse;
import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;
import com.kodbook.userservice.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class InternalUserProfileController implements UserProfileInternalApi {
    private final UserProfileService userProfileService;

    @Override
    public ResponseEntity<ApiResponse<UserProfileRegisterDto>> createUserProfile(UserProfileRegisterDto userProfileRegisterDto) {
        UserProfileRegisterDto userProfile = userProfileService.createUserProfile(userProfileRegisterDto);
        ApiResponse<UserProfileRegisterDto> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("User profile created successfully");
        apiResponse.setData(userProfile);
        apiResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
