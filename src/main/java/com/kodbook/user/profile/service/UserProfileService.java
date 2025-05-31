package com.kodbook.user.profile.service;


import com.kodbook.user.profile.dto.UserProfileRegisterDto;

public interface UserProfileService {

    UserProfileRegisterDto createUserProfile(UserProfileRegisterDto userProfileRegisterDto);
}
