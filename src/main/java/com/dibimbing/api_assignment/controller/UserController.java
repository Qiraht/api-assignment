package com.dibimbing.api_assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dibimbing.api_assignment.dtos.UserReqLogin;
import com.dibimbing.api_assignment.dtos.UserReqRegister;
import com.dibimbing.api_assignment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserReqRegister request) {
        userService.registerUser(request);
        
        
        return ResponseEntity.ok("Register Successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserController(@RequestBody UserReqLogin request) {
       return userService.loginUser(request);
    }
}
