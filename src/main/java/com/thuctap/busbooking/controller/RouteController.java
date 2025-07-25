package com.thuctap.busbooking.controller;

import com.cloudinary.Api;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Route;
import com.thuctap.busbooking.service.auth.RouteService;
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
public class RouteController {
    RouteService routeService;

    @GetMapping("/get-route/{id}")
    ApiResponse<List<Route>> getList(@PathVariable int id){
        return ApiResponse.<List<Route>>builder()
                .result(routeService.getListRoute(id))
                .build();
    }
}
