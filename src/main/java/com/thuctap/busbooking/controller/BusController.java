package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.BusType;
import com.thuctap.busbooking.service.auth.BusService;
import com.thuctap.busbooking.service.auth.BusTypeService;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BusController {

    BusService busService;


    @GetMapping("/get-all-bus")
    public ApiResponse<List<Bus>> getAllBus() {
        return ApiResponse.<List<Bus>>builder()
                .result(busService.getAllBus())
                .message("Lấy danh sách xe thành công")
                .build();
    }

    @GetMapping("/get-all-bus-type")
    public ApiResponse<List<BusType>> getAllBusType() {
        return ApiResponse.<List<BusType>>builder()
                .result(busService.getAllBusType())
                .message("Lấy danh sách xe thành công")
                .build();
    }
}
