package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.RoleRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.service.auth.RoleService;
import com.thuctap.busbooking.service.impl.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/role")
public class RoleController {

    RoleServiceImpl roleService;

    @PostMapping
    ApiResponse<Role> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<Role>builder()
                .code(200)
                .message("Role create successful!")
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<Role>> getRoles(){
        return ApiResponse.<List<Role>>builder()
                .code(200)
                .result(roleService.getRoles())
                .build();
    }
}
