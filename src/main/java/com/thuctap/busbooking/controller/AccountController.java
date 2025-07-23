package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.PasswordResetRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.UserCreationResponse;
import com.thuctap.busbooking.entity.Account;
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
@RequestMapping()
public class AccountController {
    AccountServiceImpl accountService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ApiResponse<List<Account>> getAllAccount(){
        return ApiResponse.<List<Account>>builder()
                .result(accountService.getAllAccount())
                .message("asas")
                .build();
    }

    @PostMapping("/reset-pass")
    ApiResponse resetPass(@RequestBody PasswordResetRequest request){
        boolean result = accountService.resetPass(request);
        if(result) {
            return ApiResponse.builder()
                    .message("RestPassword successfully!")
                    .build();
        }
        return ApiResponse.builder()
                .code(400)
                .message("RestPassword fail!")
                .build();
    }

}
