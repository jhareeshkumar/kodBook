package com.kodbook.userservice.profile.api.internal;

import com.kodbook.userservice.profile.dto.ApiResponse;
import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Internal User Profile API", description = "Endpoints for internal user profile management")
@RequestMapping("/internal/api/v3/users")
public interface UserProfileInternalApi {

    @Operation(
            summary = "Create User Profile",
            description = "Creates a new user profile with the provided details."
//            operationId = "createUserProfile"
    )
    @PostMapping
    ResponseEntity<ApiResponse<UserProfileRegisterDto>> createUserProfile(@RequestBody UserProfileRegisterDto userProfileRegisterDto);
}
