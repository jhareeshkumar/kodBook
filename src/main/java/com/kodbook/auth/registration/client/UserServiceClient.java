package com.kodbook.auth.registration.client;

import com.kodbook.user.profile.dto.ApiResponse;
import com.kodbook.user.profile.dto.UserProfileRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient {
    private final RestTemplate restTemplate;
    @Value("${services.user.base-url:http://localhost:8081}")
    private String userServiceBaseUrl;

    public void createUserProfile(UserProfileRegisterDto userProfileRegisterDto) {
        try {
            restTemplate.postForObject(userServiceBaseUrl + "/internal/api/v3/users", userProfileRegisterDto, ApiResponse.class);
            log.info("User profile created successfully for public id:{} username: {}", userProfileRegisterDto.getPublicId(), userProfileRegisterDto.getUsername());
        } catch (RestClientException e) {
            log.error("Failed to create user profile for public id:{} username: {}. Error {} ", userProfileRegisterDto.getPublicId(), userProfileRegisterDto.getUsername(), e.getMessage(), e);
        }
    }
}
