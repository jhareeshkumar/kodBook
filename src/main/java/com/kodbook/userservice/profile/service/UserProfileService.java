package com.kodbook.userservice.profile.service;


import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;

public interface UserProfileService {

    UserProfileRegisterDto createUserProfile(UserProfileRegisterDto userProfileRegisterDto);
}
