package com.nixon.TO_DO.controller;

import com.nixon.TO_DO.dto.request.UserRequest;
import com.nixon.TO_DO.dto.response.UserResponse;
import com.nixon.TO_DO.entity.User;
import com.nixon.TO_DO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest request){
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
