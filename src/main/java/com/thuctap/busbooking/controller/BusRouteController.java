package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.BusRouteFilterRequest;
import com.thuctap.busbooking.dto.request.BusRouteRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.BusStationUpdateResponse;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.service.impl.BusRouteServiceImpl;
import com.thuctap.busbooking.service.impl.InvoiceServiceImpl;
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
public class BusRouteController {

    BusRouteServiceImpl busRouteService;

    @GetMapping("/list-bus-route")
    ApiResponse<List<BusRoute>> getAllBusRoutes() {
        return ApiResponse.<List<BusRoute>>builder()
                .result(busRouteService.getAllBusRoutes())
                .message("Lấy danh sách tuyến xe thành công")
                .build();
    }

    @DeleteMapping("/delete-route/{id}")
    public ApiResponse<Void> deleteBusRoute(@PathVariable int id) {
        busRouteService.deleteBusRoute(id);
        return ApiResponse.<Void>builder()
                .message("Đã cập nhật trạng thái tuyến xe thành 'đã xóa'")
                .build();
    }

    @PutMapping("/restore-route/{id}")
    public ApiResponse<Void> restoreBusRoute(@PathVariable int id) {
        busRouteService.restoreBusRoute(id);
        return ApiResponse.<Void>builder()
                .message("Đã cập nhật trạng thái tuyến xe thành 'đang hoạt động'")
                .build();
    }

    @PutMapping("/update-route/{id}")
    public ApiResponse<BusRoute> updateBusRoute(@PathVariable int id, @RequestBody BusRouteRequest request) {
        return ApiResponse.<BusRoute>builder()
                .result(busRouteService.updateBusRoute(id, request))
                .message("Cập nhật tuyến xe thành công")
                .build();
    }

    @PostMapping("/add-new-route")
    public ApiResponse<BusRoute> addBusRoute(@RequestBody BusRouteRequest request) {
        return ApiResponse.<BusRoute>builder()
                .result(busRouteService.addBusRoute(request))
                .message("Thêm tuyến xe thành công")
                .build();
    }

    @PostMapping("/filter-route")
    public ApiResponse<List<BusRoute>> filterBusRoutes(@RequestBody BusRouteFilterRequest request) {
        List<BusRoute> filteredRoutes = busRouteService.filterBusRoutes(
                request.getFrom(),
                request.getTo(),
                request.getDistance(),
                request.getTravelTime(),
                request.getStatus()
        );

        return ApiResponse.<List<BusRoute>>builder()
                .result(filteredRoutes)
                .message("Lọc danh sách tuyến xe thành công")
                .build();
    }


}
