package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.request.RegisterRequest;
import com.thuctap.busbooking.dto.request.VerifyRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.JwtResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.service.auth.AuthService;
import com.thuctap.busbooking.service.impl.AccountServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping()
public class AuthController {
    AuthService authService;
    AccountServiceImpl accountService;

    @PostMapping("/login")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.<JwtResponse>builder()
                .result(authService.login(loginRequest))
                .build();
    }
    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest request) {
        String result = accountService.sendVerificationEmail(request.getEmail());
        return ApiResponse.builder()
                .code(200)
                .message("Email sent successfully!")
                .build();
    }

    @PostMapping("/verify")
    public ApiResponse<?> verify(@RequestBody VerifyRequest request) {
        if (accountService.verifyEmail(request.getEmail(), request.getCode())) {
            return ApiResponse.builder()
                    .code(200)
                    .message("Successful authentication!")
                    .build();
        }
        return ApiResponse.builder()
                .code(ErrorCode.INVALID_OTP.getCode())
                .message(ErrorCode.INVALID_OTP.getMessage())
                .build();
    }

    @PostMapping("/create-account")
    public ApiResponse<?> createAccount(@RequestBody AccountCreationRequest request) {
        accountService.createAccountUser(request);
        return ApiResponse.builder()
                .code(200)
                .message("Account created successfully!")
                .build();
    }
}
