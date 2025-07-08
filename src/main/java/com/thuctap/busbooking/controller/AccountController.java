package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.service.impl.AccountServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin")
public class AccountController {

    AccountServiceImpl accountService;
    @GetMapping
    ApiResponse<List<Account>> getAllAccount(){
        return ApiResponse.<List<Account>>builder()
                .result(accountService.getAllAccount())
                .message("asas")
                .build();
    }

}
