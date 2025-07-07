package com.nixon.TO_DO.controller;

import com.nixon.TO_DO.dto.request.UserRequest;
import com.nixon.TO_DO.dto.response.TaskListResponse;
import com.nixon.TO_DO.dto.response.UserResponse;
import com.nixon.TO_DO.entity.User;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import com.nixon.TO_DO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                userService.getUserById(id)
                        .map(
                                (user) -> new UserResponse(
                                        user.getId(),
                                        user.getUsername(),
                        user.getPassword(),
                                        user.getTaskList().stream().map(
                                                (list) -> new TaskListResponse(
                                                        list.getUser().getUsername(),
                                                        list.getId(),
                                                        list.getTitle(),
                                                        list.getDescription(),
                                                        list.getCreationDate(),
                                                        list.getLastUpdate())
                                        ).collect(Collectors.toList()))
                        ).orElseThrow(
                                () -> new EntityNotFoundException("User Not Found")
                        )
        );
    }
}
