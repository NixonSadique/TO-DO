package com.nixon.TO_DO.service;

import com.nixon.TO_DO.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    //String updateUser(Long id, User user);
}
