package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.SeatPositionResponse;
import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.service.auth.SeatPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class SeatPositionController {
    SeatPositionService seatPositionService;
    @GetMapping("/seats/bus/{tripId}")
    public ApiResponse<List<SeatPosition>> getSeatsByBusId(@PathVariable int tripId) {
        List<SeatPosition> seats = seatPositionService.getSeatsByBusId(tripId);
        return ApiResponse.<List<SeatPosition>>builder()
                .result(seats)
                .build();
    }
}
