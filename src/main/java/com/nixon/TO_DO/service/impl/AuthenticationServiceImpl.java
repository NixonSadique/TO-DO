package com.nixon.TO_DO.service.impl;

import com.nixon.TO_DO.dto.request.AuthenticationRequest;
import com.nixon.TO_DO.dto.response.TokenResponse;
import com.nixon.TO_DO.entity.User;
import com.nixon.TO_DO.exception.BadRequestException;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import com.nixon.TO_DO.repository.UserRepository;
import com.nixon.TO_DO.security.TokenService;
import com.nixon.TO_DO.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final AuthenticationManager authManager;
    TokenService tokenService;

    @Override
    public TokenResponse authenticate(AuthenticationRequest request) {
        User user = repository.
                findByUsername(request.username())
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found!")
                );

        var token = tokenService.generateToken(user);

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );


        return new TokenResponse(token, user.getId());
    }
}
