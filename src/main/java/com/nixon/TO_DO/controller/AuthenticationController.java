package com.nixon.TO_DO.controller;

import com.nixon.TO_DO.dto.request.AuthenticationRequest;
import com.nixon.TO_DO.dto.response.TokenResponse;
import com.nixon.TO_DO.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todo/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> userLogin(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
