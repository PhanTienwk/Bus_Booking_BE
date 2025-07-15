package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.BusFilterRequest;
import com.thuctap.busbooking.dto.request.BusRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.BusType;
import com.thuctap.busbooking.service.auth.BusService;
import com.thuctap.busbooking.service.auth.BusTypeService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add-bus")
    public ApiResponse<Bus> addBus(@RequestBody BusRequest busRequest) {
        Bus bus = busService.addBus(busRequest);
        return ApiResponse.<Bus>builder()
                .result(bus)
                .message("Thêm xe thành công")
                .code(1000)
                .build();
    }

    @PutMapping("/update-bus/{id}")
    public ApiResponse<Bus> updateBus(@PathVariable int id, @RequestBody BusRequest busRequest) {
        Bus bus = busService.updateBus(id, busRequest);
        return ApiResponse.<Bus>builder()
                .result(bus)
                .message("Cập nhật xe thành công")
                .code(1000)
                .build();
    }

    @PutMapping("/update-bus-status/{id}")
    public ApiResponse<Bus> updateBusStatus(@PathVariable int id, @RequestParam int status) {
        Bus bus = busService.updateBusStatus(id, status);
        return ApiResponse.<Bus>builder()
                .result(bus)
                .message("Cập nhật trạng thái xe thành công")
                .code(1000)
                .build();
    }

    @PostMapping("/filter-buses")
    public ApiResponse<List<Bus>> filterBuses(@RequestBody BusFilterRequest request) {
        return ApiResponse.<List<Bus>>builder()
                .result(busService.filterBuses(request))
                .message("Lọc danh sách xe thành công")
                .code(1000)
                .build();
    }
}
