package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.service.auth.BusStationService;
import com.thuctap.busbooking.service.impl.AccountServiceImpl;
import com.thuctap.busbooking.service.impl.BusStationServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/bus-station")
public class BusStationController {
    BusStationServiceImpl busStationService;

 
    @GetMapping
    public ApiResponse<List<BusStation>> getAllBusStation() {
        return ApiResponse.<List<BusStation>>builder()
                .result(busStationService.getAllBusSTT())
                .message("Lấy danh sách bến xe thành công")
                .build();
    }

}
