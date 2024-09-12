package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.UserResponse;
import com.jekirdek.crmcustomer.entity.User;

import java.util.List;

public interface UserService {

    UserResponse save(User user);

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    void deleteById(Long id);

    User findByUserName(String username);
}
