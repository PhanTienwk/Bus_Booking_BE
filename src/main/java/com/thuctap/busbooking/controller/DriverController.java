package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.UserFilterRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.DriverScheduleResponse;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.service.impl.DriverServiceImpl;
import com.thuctap.busbooking.service.impl.UserServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverController {

    DriverServiceImpl driverService;

    @GetMapping("/list-driver")
    ApiResponse<List<User>> getAllDrivers() {
        return ApiResponse.<List<User>>builder()
                .result(driverService.getAllDrivers())
                .message("Lấy danh sách tài xế thành công")
                .build();
    }

    @PostMapping("/filter-driver")
    public ApiResponse<List<User>> filterDrivers(@RequestBody UserFilterRequest request) {
        List<User> filteredDriers = driverService.filterDrivers(
                request.getName(),
                request.getGender(),
                request.getBirthday(),
                request.getPhone(),
                request.getEmail(),
                request.getStatus(),
                request.getRoleId()
        );

        return ApiResponse.<List<User>>builder()
                .result(filteredDriers)
                .message("Lọc danh sách tài xế thành công")
                .build();
    }

    @GetMapping("/schedule/{driverId}")
    public ApiResponse<List<DriverScheduleResponse>> getDriverScheduleByWeek(
            @PathVariable Integer driverId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<DriverScheduleResponse> schedules = driverService.getScheduleByDriverAndDateRange(driverId, startDate, endDate);

        return ApiResponse.<List<DriverScheduleResponse>>builder()
                .result(schedules)
                .message("Lấy lịch chạy xe thành công")
                .build();
    }

}
