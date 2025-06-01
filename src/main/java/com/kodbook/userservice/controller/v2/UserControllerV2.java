package com.kodbook.userservice.controller.v2;

import com.kodbook.userservice.api.UserApi;
import com.kodbook.userservice.dto.PaginationRequest;
import com.kodbook.userservice.dto.UserDto;
import com.kodbook.userservice.service.v2.UserServiceV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<UserDto>> getUsers(@ModelAttribute @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(userService.getUsers(paginationRequest));
    }
}
