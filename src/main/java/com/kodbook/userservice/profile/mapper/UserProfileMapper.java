package com.kodbook.userservice.profile.mapper;

import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;
import com.kodbook.userservice.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toEntity(UserProfileRegisterDto userProfileRegisterDto);

    UserProfileRegisterDto toDto(UserProfile userProfile);
}
