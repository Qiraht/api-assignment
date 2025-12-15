package com.dibimbing.api_assignment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public ResponseEntity<String> registerUser(UserReqRegister request) {
        
        User user = new User();

        // email empty & format validation
        if (!StringUtils.hasText(request.getEmail())) {
            return ResponseEntity.status(400).body("Silahkan isi Email");
        }

        // password empty validation
        if (!StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.status(400).body("Silahkan isi Password");
        }

        // username empty validation
        if (!StringUtils.hasText(request.getUsername())) {
            return ResponseEntity.status(400).body("Silahkan isi username");
        }

        // address empty validation
        if (!StringUtils.hasText(request.getAddress())) {
            return ResponseEntity.status(400).body("Silahkan isi alamat");
        }

        // email duplicate validation
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email sudah terpakai");
        }

        // username duplicate validation
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(400).body("Username sudah terpakai");
        }

        BeanUtils.copyProperties(request, user);

        userRepo.save(user);

        return ResponseEntity.ok("Register berhasil");
    }

    public ResponseEntity<String> loginUser(UserReqLogin request) {
        Optional<User> userOpt = userRepo.findByUsername(request.getUsername());

        // username validation
        if (!StringUtils.hasText(request.getUsername())) {
            return ResponseEntity.status(400).body("Silahkan isi username");
        }

        // password empty validation
        if (!StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.status(400).body("Silahkan isi Password");
        }

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Username tidak ditemukan");
         }

        User user = userOpt.get();

        // password credential validation
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Kredensial salah, silahkan masukkan ulang");
        }

        return ResponseEntity.ok("Register successful");
        
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
