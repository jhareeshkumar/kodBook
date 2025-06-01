package com.kodbook.authservice.mapper;

import com.kodbook.authservice.dto.AuthUserDto;
import com.kodbook.authservice.entity.AuthUser;
import com.kodbook.authservice.registration.dto.RegisterRequest;
import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUser toAuthUserEntity(RegisterRequest registerRequest);

    AuthUser toEntity(AuthUserDto authUserDto);

    AuthUserDto toAuthUserDto(AuthUser authUser);

    UserProfileRegisterDto toUserProfileRegisterDto(AuthUser authUser);
}
