package com.kodbook.auth.mapper;

import com.kodbook.auth.dto.AuthUserDto;
import com.kodbook.auth.entity.AuthUser;
import com.kodbook.auth.registration.dto.RegisterRequest;
import com.kodbook.user.profile.dto.UserProfileRegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUser toAuthUserEntity(RegisterRequest registerRequest);

    AuthUser toEntity(AuthUserDto authUserDto);

    AuthUserDto toAuthUserDto(AuthUser authUser);

    UserProfileRegisterDto toUserProfileRegisterDto(AuthUser authUser);
}
