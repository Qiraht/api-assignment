package com.dibimbing.api_assignment.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dibimbing.api_assignment.dtos.UserReqRegister;
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
    
}
