package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.ProvinceRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.service.impl.BusTripServiceImpl;
import com.thuctap.busbooking.service.impl.ProvinceServiceImpl;
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



    @PostMapping("/add-province")
    public ApiResponse<ProvinceRequest> addProvince(@RequestBody ProvinceRequest dto) {
        try {
            ProvinceRequest createdProvince = provinceService.addProvince(dto);
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1000)
                    .result(createdProvince)
                    .message("Thêm tỉnh/thành phố thành công")
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Invalid input for adding province: {}", e.getMessage());
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1001)
                    .result(null)
                    .message("Dữ liệu không hợp lệ: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            log.error("Error adding province: {}", e.getMessage());
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1002)
                    .result(null)
                    .message("Lỗi khi thêm tỉnh/thành phố: " + e.getMessage())
                    .build();
        }
    }

    @PutMapping("update-province/{id}")
    public ApiResponse<ProvinceRequest> updateProvince(
            @PathVariable int id, @RequestBody ProvinceRequest dto) {
        try {
            ProvinceRequest updatedProvince = provinceService.updateProvince(id, dto);
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1000)
                    .result(updatedProvince)
                    .message("Cập nhật tỉnh/thành phố thành công")
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Invalid input for updating province: {}", e.getMessage());
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1001)
                    .result(null)
                    .message("Dữ liệu không hợp lệ: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            log.error("Error updating province: {}", e.getMessage());
            return ApiResponse.<ProvinceRequest>builder()
                    .code(1002)
                    .result(null)
                    .message("Lỗi khi cập nhật tỉnh/thành phố: " + e.getMessage())
                    .build();
        }
    }

    @PutMapping("update-province-status/{id}")
    public ApiResponse<Void> updateProvinceStatus(
            @PathVariable int id, @RequestParam int status) {
        try {
            provinceService.updateProvinceStatus(id, status);
            return ApiResponse.<Void>builder()
                    .code(1000)
                    .result(null)
                    .message("Cập nhật trạng thái tỉnh/thành phố thành công")
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Invalid status for province: {}", e.getMessage());
            return ApiResponse.<Void>builder()
                    .code(1001)
                    .result(null)
                    .message("Trạng thái không hợp lệ: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            log.error("Error updating province status: {}", e.getMessage());
            return ApiResponse.<Void>builder()
                    .code(1002)
                    .result(null)
                    .message("Lỗi khi cập nhật trạng thái tỉnh/thành phố: " + e.getMessage())
                    .build();
        }
    }
}
