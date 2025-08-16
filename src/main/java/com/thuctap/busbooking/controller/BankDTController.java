package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.BankDetails;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.service.auth.BankDetailService;
import com.thuctap.busbooking.service.auth.BusService;
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
public class BankDTController {
    BankDetailService bankDetailService;


    @GetMapping("/get-all-bank")
    public ApiResponse<List<BankDetails>> getAllBankDT() {
        return ApiResponse.<List<BankDetails>>builder()
                .result(bankDetailService.getAllBankDT())
                .message("Lấy danh sách ngân hàng thành công!")
                .build();
    }
}
