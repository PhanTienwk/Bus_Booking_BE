package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.BusStationFilterRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.BusStationAddResponse;
import com.thuctap.busbooking.dto.response.BusStationUpdateResponse;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.service.impl.BusStationServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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

public class BusStationController {
    BusStationServiceImpl busStationService;
    @GetMapping("/bus-station")
    public ApiResponse<List<BusStation>> getAllBusStation() {
        return ApiResponse.<List<BusStation>>builder()
                .result(busStationService.getAllBusSTT())
                .message("Lấy danh sách bến xe thành công")
                .build();
    }

    @PutMapping("/update-bus-station")
    public ApiResponse<Boolean> updateBusStation(@RequestBody BusStationUpdateResponse request) {

        return ApiResponse.<Boolean>builder()
                .result(busStationService.updateBusStation(request))
                .message("Cập nhật bến xe thành công")
                .build();
    }

    @PostMapping("/add-new-bus-station")
    public ApiResponse<BusStation> addBusStation(@RequestBody BusStationAddResponse request) {
        return ApiResponse.<BusStation>builder()
                .result(busStationService.addBusStation(request))
                .message("Thêm bến xe thành công")
                .build();
    }
    @PostMapping("/filter-bus-station")
    public ApiResponse<List<BusStation>> filterBusStations(@RequestBody BusStationFilterRequest request) {
        return ApiResponse.<List<BusStation>>builder()
                .result(busStationService.filterBusStations(
                        request.getId(),
                        request.getName(),
                        request.getAddress(),
                        request.getPhone(),
                        request.getProvinceId(),
                        request.getStatus()
                ))
                .message("Lọc danh sách bến xe thành công")
                .build();
    }
    @PutMapping("/update-bus-station-status")
    public ApiResponse<Boolean> updateBusStationStatus(@RequestParam Integer id, @RequestParam Integer status) {
        return ApiResponse.<Boolean>builder()
                .result(busStationService.updateBusStationStatus(id, status))
                .message("Cập nhật trạng thái bến xe thành công")
                .build();
    }
}
