package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.request.RegisterRequest;
import com.thuctap.busbooking.dto.request.VerifyRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.JwtResponse;
import com.thuctap.busbooking.entity.Account;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = accountService.sendVerificationEmail(request.getEmail());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyRequest request) {
        if (accountService.verifyEmail(request.getEmail(), request.getCode())) {
            return ResponseEntity.ok("Email xác minh thành công!");
        }
        return ResponseEntity.badRequest().body("Mã OTP không hợp lệ!");
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreationRequest request) {
        accountService.createAccountUser(request);
        return ResponseEntity.ok("Tạo tài khoản thành công!");
    }
}
