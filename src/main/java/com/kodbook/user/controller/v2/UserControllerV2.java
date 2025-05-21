package com.kodbook.user.controller.v2;

import com.kodbook.user.api.UserApi;
import com.kodbook.user.dto.UserDto;
import com.kodbook.user.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<UserDto>> getUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                  @RequestParam(defaultValue = "ASC", required = false) String sortDirection,
                                                  @RequestParam(defaultValue = "id", required = false) String[] sortBy) {
        return ResponseEntity.ok(userService.getUsers(pageNumber, pageSize, sortDirection, sortBy));
    }
}
