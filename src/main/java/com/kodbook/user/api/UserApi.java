package com.kodbook.user.api;

import com.kodbook.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v2/users")
public interface UserApi {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();
}
