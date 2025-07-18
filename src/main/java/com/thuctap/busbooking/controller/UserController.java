package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.UserFilterRequest;
import com.thuctap.busbooking.dto.request.UserRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.service.auth.UserService;
import com.thuctap.busbooking.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    UserServiceImpl userService;
    @GetMapping("/list-user")
    ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.<List<User>>builder()
                .result(userService.getAllUsers())
                .message("Lấy danh sách người dùng thành công")
                .build();
    }

    @GetMapping("user/{id}")
    public ApiResponse<User> getUserById(@PathVariable int id) {
        return ApiResponse.<User>builder()
                .result(userService.getUserById(id))
                .message("Lấy người dùng thành công")
                .build();
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        return ApiResponse.<User>builder()
                .result(userService.createUser(user))
                .message("Tạo người dùng thành công")
                .build();
    }

    @PutMapping("/update-user/{id}")
    public ApiResponse<User> updateUser(@PathVariable int id, @RequestBody UserRequest request) {
        return ApiResponse.<User>builder()
                .result(userService.updateUser(id, request))
                .message("Cập nhật người dùng thành công")
                .build();
    }

    @DeleteMapping("/delete-user/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message("Đã cập nhật trạng thái người dùng thành 'đã xóa'")
                .build();
    }

    @PutMapping("/restore-user/{id}")
    public ApiResponse<Void> restoreUser(@PathVariable int id) {
        userService.restoreUser(id);
        return ApiResponse.<Void>builder()
                .message("Đã cập nhật trạng thái người dùng thành 'đang hoạt động'")
                .build();
    }

    @PostMapping("/filter-user")
    public ApiResponse<List<User>> filterUsers(@RequestBody UserFilterRequest request) {
        List<User> filteredUsers = userService.filterUsers(
                request.getName(),
                request.getGender(),
                request.getBirthday(),
                request.getPhone(),
                request.getEmail(),
                request.getStatus(),
                request.getRoleId()
        );

        return ApiResponse.<List<User>>builder()
                .result(filteredUsers)
                .message("Lọc danh sách người dùng thành công")
                .build();
    }

}
