package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.service.impl.BusTripServiceImpl;
import com.thuctap.busbooking.service.impl.InvoiceServiceImpl;
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
@RequestMapping("/admin")
public class BusTripController {

    BusTripServiceImpl busTripService;

    @GetMapping("/list-bus-trip")
    ApiResponse<List<BusTrip>> getAllBusTrips() {
        return ApiResponse.<List<BusTrip>>builder()
                .result(busTripService.getAllBusTrips())
                .message("Lấy danh sách chuyến xe thành công")
                .build();
    }
}
