package com.nixon.TO_DO.service;

import com.nixon.TO_DO.dto.request.AuthenticationRequest;
import com.nixon.TO_DO.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequest request);
}
