package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.LoginRequest;
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
    public ResponseEntity<?> register(@RequestParam String email) {
        String result = accountService.sendVerificationEmail(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String email, @RequestParam String code, @RequestParam String password) {
        if (accountService.verifyEmail(email, code)) {
            return ResponseEntity.ok("Account created successfully! ");
        }
        return ResponseEntity.badRequest().body("Invalid verification code!");
    }
}
