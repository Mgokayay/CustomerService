package com.jekirdek.crmcustomer.controller;

import com.jekirdek.crmcustomer.dto.UserLoginResponse;
import com.jekirdek.crmcustomer.dto.UserResponse;
import com.jekirdek.crmcustomer.entity.User;
import com.jekirdek.crmcustomer.exception.CommonException;
import com.jekirdek.crmcustomer.repository.UserRepository;
import com.jekirdek.crmcustomer.service.UserService;
import com.jekirdek.crmcustomer.util.UserDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponse addUser(@RequestBody User user) {
         return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginResponse userLoginResponse) {
        try {

            User user = userService.findByUserName(userLoginResponse.username());

            // Şifre kontrolünü yap (şifreler plain text tutulmamalı)
            if (passwordEncoder.matches(userLoginResponse.password(), user.getPassword())) {
                return ResponseEntity.ok(UserDtoConversion.convertUser(user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        } catch (CommonException ex) {
            // Kullanıcı bulunamazsa hata dön
            return ResponseEntity.status(ex.getHttpStatus()).body(null);
        }
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }



}
