package com.dibimbing.api_assignment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dibimbing.api_assignment.dtos.UserReqRegister;
import com.dibimbing.api_assignment.dtos.UserResponse;
import com.dibimbing.api_assignment.dtos.UserReqLogin;
import com.dibimbing.api_assignment.entity.User;
import com.dibimbing.api_assignment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public void registerUser(UserReqRegister request) {
        User user = new User();
        
        BeanUtils.copyProperties(request, user);

        userRepo.save(user);
    }

    public ResponseEntity<String> loginUser(UserReqLogin request) {
         Optional<User> userOpt = userRepo.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Email not found / incorrect email");
         }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(400).body("Incorrect password");
        }

        return ResponseEntity.ok("Login successful");
        
    }

    public ResponseEntity<List<UserResponse>> getUserById(Long id) {
        List<UserResponse> userResponse =  new ArrayList<>();

        Optional<User> users = userRepo.findById(id);
        if(users.isPresent()) {
            UserResponse userResp = new UserResponse();
            BeanUtils.copyProperties(users.get(), userResp);

            userResponse.add(userResp);
            return ResponseEntity.ok(userResponse);
        }

        return ResponseEntity.status(404).build();
    }
    
}
