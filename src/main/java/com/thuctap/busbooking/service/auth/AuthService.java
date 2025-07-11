package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
}
