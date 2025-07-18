package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.service.impl.BusTripServiceImpl;
import com.thuctap.busbooking.service.impl.ProvinceServiceImpl;
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
public class ProvinceController {

    ProvinceServiceImpl provinceService;

    @GetMapping("/list-province")
    ApiResponse<List<Province>> getAllProvinces() {
        return ApiResponse.<List<Province>>builder()
                .result(provinceService.getAllProvinces())
                .message("Lấy danh sách tỉnh thành thành công")
                .build();
    }

    @GetMapping("/get-all-province")
    public ApiResponse<List<Province>> getAllProvince() {
        return ApiResponse.<List<Province>>builder()
                .result(provinceService.getAllProvince())
                .message("Lấy danh sách thành phố")
                .build();
    }
}
