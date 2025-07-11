package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.dto.response.AccountResponse;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.service.auth.AccountService;
import com.thuctap.busbooking.service.impl.AccountServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ApiResponse<List<Account>> getAllAccount(){
        return ApiResponse.<List<Account>>builder()
                .result(accountService.getAllAccount())
                .message("asas")
                .build();
    }

    @PostMapping
    ApiResponse<AccountResponse> createAccountUser(@RequestBody AccountCreationRequest request){
        Account account = accountService.createAccountUser(request);
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .message("Create account successful!")
                .result(AccountResponse.builder()
                        .email(account.getEmail())
                        .role(account.getRole())
                        .build())
                .build();
    }
}
