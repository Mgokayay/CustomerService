package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.UserResponse;
import com.jekirdek.crmcustomer.entity.User;
import com.jekirdek.crmcustomer.exception.CommonException;
import com.jekirdek.crmcustomer.repository.UserRepository;
import com.jekirdek.crmcustomer.util.UserDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.jekirdek.crmcustomer.util.UserDtoConversion.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return convertUser(userRepository.save(user));
    }

    @Override
    public List<UserResponse> findAll() {

        return convertUserList(userRepository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
             return convertUser(userOptional.get());
        }
        throw new CommonException("User not found with given id" + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.delete(userOptional.get());
        }else{
            throw new CommonException("User not found with given id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User findByUserName(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new CommonException("User not found with given username" + username, HttpStatus.NOT_FOUND);

    }
}
