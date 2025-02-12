package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public List<String> getUsersList() {
        return List.of("User1", "User2", "User3");
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable String userId) {
        return "Twoja nazwa uzytkownika to: " + userId;
    }

    @PostMapping
    public String createUser(@RequestBody String user) {
        return "Uzytkownik zostal utworzony";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "Uzytkownik " + userId + " zostal usuniety";
    }

    @PutMapping
    public String updateUser(@RequestBody String user) {
        return "Uzytkownik zostal zaktualizowany";
    }

}
