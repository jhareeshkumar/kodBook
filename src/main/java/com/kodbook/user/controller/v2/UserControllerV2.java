package com.kodbook.user.controller.v2;

import com.kodbook.user.api.UserApi;
import com.kodbook.user.dto.UserDto;
import com.kodbook.user.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerV2 implements UserApi {

    private final UserServiceV2 userService;

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        UserDto userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
