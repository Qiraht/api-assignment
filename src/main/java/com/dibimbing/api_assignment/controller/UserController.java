package com.dibimbing.api_assignment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dibimbing.api_assignment.dtos.UserReqRegister;
import com.dibimbing.api_assignment.dtos.UserResponse;
import com.dibimbing.api_assignment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public ResponseEntity<String> registerUser(UserReqRegister request) {
        return userService.registerUser(request);

    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUserController(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserResponse>> getUserByIdController(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
