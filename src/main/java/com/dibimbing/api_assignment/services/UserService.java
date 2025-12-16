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
            System.err.println("error: email tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi Email");
        }

        // password empty validation
        if (!StringUtils.hasText(request.getPassword())) {
            System.err.println("error: password tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi Password");
        }

        // username empty validation
        if (!StringUtils.hasText(request.getUsername())) {
            System.err.println("error: username tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi username");
        }

        // address empty validation
        if (!StringUtils.hasText(request.getAddress())) {
            System.err.println("error: alamat tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi alamat");
        }

        // email duplicate validation
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            System.err.println("error: email sudah terpakai");
            return ResponseEntity.status(400).body("Email sudah terpakai");
        }

        // username duplicate validation
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            System.err.println("error: username sudah terpakai");
            return ResponseEntity.status(400).body("Username sudah terpakai");
        }

        BeanUtils.copyProperties(request, user);

        userRepo.save(user);

        System.err.println("Register berhasil");
        return ResponseEntity.status(201).body("Register berhasil");
    }

    public ResponseEntity<String> loginUser(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);

        // username validation
        if (!StringUtils.hasText(username)) {
            System.err.println("error: username tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi username");
        }

        // password empty validation
        if (!StringUtils.hasText(password)) {
            System.err.println("error: password tidak diisi");
            return ResponseEntity.status(400).body("Silahkan isi Password");
        }

        if (userOpt.isEmpty()) {
            System.err.println("error: username tidak ditemukan");
            return ResponseEntity.status(404).body("Username tidak ditemukan");
        }

        User user = userOpt.get();

        // password credential validation
        if (!user.getPassword().equals(password)) {
            System.err.println("error: kredensial login salah");
            return ResponseEntity.status(401).body("Kredensial salah, silahkan masukkan ulang");
        }

        System.out.println("Login berhasil");
        return ResponseEntity.ok("Login berhasil");
        
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

        System.err.println("error: id tidak ditemukan");
        return ResponseEntity.status(404).build();
    }
    
}
