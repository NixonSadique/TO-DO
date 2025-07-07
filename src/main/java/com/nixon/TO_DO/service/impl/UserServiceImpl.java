package com.nixon.TO_DO.service.impl;

import com.nixon.TO_DO.entity.User;
import com.nixon.TO_DO.exception.BadRequestException;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import com.nixon.TO_DO.repository.UserRepository;
import com.nixon.TO_DO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String createUser(User user) {
        if (this.userRepository.existsByUsername(user.getUsername())){
            throw new BadRequestException("There is already an user with that username");
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return "User created with success";
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

//    @Override
//    public String updateUser(Long id, User user) {
//        if (this.userRepository.existsByUsername(user.getUsername())){
//            throw new BadRequestException("There is already a user with that username");
//        }
//
//        User rawUser = userRepository.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("User not found!")
//        );
//
//        rawUser.setUsername(user.getUsername());
//        rawUser.setPassword(user.getPassword());
//
//        userRepository.save(rawUser);
//        return "";
//    }
}
