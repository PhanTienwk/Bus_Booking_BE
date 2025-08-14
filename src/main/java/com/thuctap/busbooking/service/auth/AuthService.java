package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.ForgotPasswordRequest;
import com.thuctap.busbooking.dto.request.GoogleLoginRequest;
import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.response.GoogleLoginResponse;
import com.thuctap.busbooking.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
    GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request) throws Exception;
    boolean forgotPassword(ForgotPasswordRequest request);
}
