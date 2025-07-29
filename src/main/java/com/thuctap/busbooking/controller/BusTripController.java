package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.BusTripFilterRequest;
import com.thuctap.busbooking.dto.request.BusTripRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.BusTripSearchResponse;
import com.thuctap.busbooking.dto.response.PassengerTripInfoResponse;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.service.auth.BusTripService;
import com.thuctap.busbooking.service.impl.BusRouteServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusTripController {

    BusTripService busTripService;
    BusTripRepository busTripRepository;

    @GetMapping("/get-all-bustrip")
    public ApiResponse<List<BusTrip>> getAllBusTrip() {
        return ApiResponse.<List<BusTrip>>builder()
                .result(busTripService.getAllBusTrip())
                .message("Lấy danh sách xe thành công")
                .build();
    }

    @GetMapping("/get-all-busroutee")
    public ApiResponse<List<BusRoute>> getAllBusRoutee() {
        return ApiResponse.<List<BusRoute>>builder()
                .result(busTripService.getAllBusRoutes())
                .message("Lấy danh sách xe thành công")
                .build();
    }

    @GetMapping("/get-all-userr")
    public ApiResponse<List<User>> getAllUser() {
        return ApiResponse.<List<User>>builder()
                .result(busTripService.getAllUsers())
                .message("Lấy danh sách xe thành công")
                .build();
    }

    @PutMapping("/update-bus-trip-status")
    public ApiResponse<Boolean> updateBusTripStatus(@RequestParam Integer id, @RequestParam Integer status) {
        return ApiResponse.<Boolean>builder()
                .result(busTripService.updateBusTripStatus(id, status))
                .message("Cập nhật trạng thái bến xe thành công")
                .build();
    }

    @PostMapping("/add-bus-trip")
    public ApiResponse<BusTrip> addBusTrip(@RequestBody BusTripRequest request) {
        return ApiResponse.<BusTrip>builder()
                .result(busTripService.addBusTrip(request))
                .message("Thêm chuyến xe thành công")
                .build();
    }

    @PutMapping("/update-bus-trip/{id}")
    public ApiResponse<BusTrip> updateBusTrip(@PathVariable Integer id, @RequestBody BusTripRequest request) {
        return ApiResponse.<BusTrip>builder()
                .result(busTripService.updateBusTrip(id, request))
                .message("Cập nhật chuyến xe thành công")
                .build();
    }

    @PostMapping("/filter-bus-trips")
    public ApiResponse<List<BusTrip>> filterBusTrips(@RequestBody BusTripFilterRequest request) {
        return ApiResponse.<List<BusTrip>>builder()
                .result(busTripService.filterBusTrips(request))
                .message("Lọc danh sách chuyến xe thành công")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<BusTripSearchResponse>> searchTripsByProvinces(
            @RequestParam("fromProvinceId") int fromProvinceId,
            @RequestParam("toProvinceId") int toProvinceId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("ticketCount") int count
    ) {
        LocalDateTime currentTimePlusTwoHours = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusHours(2);
        return ApiResponse.<List<BusTripSearchResponse>>builder()
                .result(busTripService.getBusTrip(fromProvinceId,toProvinceId,date,count))
                .build();
    }

    @GetMapping("/passenger-trip-info/{tripId}")
    public ApiResponse<List<PassengerTripInfoResponse>> getPassengerTripInfo(@PathVariable Integer tripId) {
        return ApiResponse.<List<PassengerTripInfoResponse>>builder()
                .result(busTripService.getPassengerTripInfoByTripId(tripId))
                .message("Lấy thông tin hành khách trên chuyến xe thành công")
                .build();
    }
}
