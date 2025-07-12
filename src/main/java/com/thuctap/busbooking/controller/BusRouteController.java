package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.service.impl.BusRouteServiceImpl;
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
public class BusRouteController {

    BusRouteServiceImpl busRouteService;

    @GetMapping("/list-bus-route")
    ApiResponse<List<BusRoute>> getAllBusRoutes() {
        return ApiResponse.<List<BusRoute>>builder()
                .result(busRouteService.getAllBusRoutes())
                .message("Lấy danh sách tuyến xe thành công")
                .build();
    }
}
