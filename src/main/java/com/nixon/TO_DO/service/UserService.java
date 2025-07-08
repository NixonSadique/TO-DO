package com.nixon.TO_DO.service;

import com.nixon.TO_DO.dto.response.UserResponse;
import com.nixon.TO_DO.entity.User;

import java.util.List;

public interface UserService {
    String createUser(User user);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    //String updateUser(Long id, User user);
}
