package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsersList() {
        return userMapper.maptoUserDto(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public Optional<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId).map(userMapper::maptoUserDto);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.maptoUserDto(userService.saveUser(userMapper.maptoUser(userDto)));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long UserId, @RequestBody UserDto userDto) {
        return userMapper.maptoUserDto(userService.updateUser(UserId, userDto.getUsername()));
    }
}
