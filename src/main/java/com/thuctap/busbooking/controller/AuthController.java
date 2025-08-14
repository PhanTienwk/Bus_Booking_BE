package com.thuctap.busbooking.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.thuctap.busbooking.dto.request.*;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.GoogleLoginResponse;
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

import java.util.Collections;
import java.util.Optional;

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

    @PostMapping("/auth/google")
    public ApiResponse<GoogleLoginResponse> googleLogin( @RequestBody GoogleLoginRequest req) throws Exception {
            GoogleLoginResponse resp = authService.loginWithGoogle(req);
            System.out.println(resp.getEmail());
            return ApiResponse.<GoogleLoginResponse>builder()
                    .result(resp)
                    .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<?> forgotPassword(@RequestBody ForgotPasswordRequest request){
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ApiResponse.<String>builder()
                    .code(101)
                    .message("Email không được để trống")
                    .build();
        }
        boolean result = authService.forgotPassword(request);
        if(result){
            return ApiResponse.<String>builder()
                    .code(1000)
                    .message("Email đặt lại mật khẩu đã được gửi")
                    .build();
        }else{
            ApiResponse.<String>builder()
                    .code(101)
                    .message("Email không tồn tại hoặc có lỗi xảy ra")
                    .build();
        }
        return ApiResponse.builder().code(101).build();
    }

}
