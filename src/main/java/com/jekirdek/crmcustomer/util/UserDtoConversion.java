package com.jekirdek.crmcustomer.util;

import com.jekirdek.crmcustomer.dto.UserResponse;
import com.jekirdek.crmcustomer.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDtoConversion {

    public static List<UserResponse> convertUserList(List<User> userList) {

        List<UserResponse> userResponseList = userList.stream().map(user -> new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        )).collect(Collectors.toList());
        return userResponseList;
    }

    public static UserResponse convertUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
