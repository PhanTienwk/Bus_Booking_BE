package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.response.JwtResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.security.jwt.JwtUtil;
import com.thuctap.busbooking.service.auth.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthServiceImpl implements AuthService {
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    AccountRepository accountRepository;

    public JwtResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Account account = accountRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            String token = jwtUtil.generateToken(account.getEmail(), account.getRole().getName());
            return new JwtResponse(token);
        } catch (DisabledException e) {
            throw new RuntimeException("Account is disabled. Please contact support.");
        }
    }

}
