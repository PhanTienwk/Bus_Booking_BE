package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.*;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.UserCreationResponse;
import com.thuctap.busbooking.dto.response.UserInfoResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.service.auth.UserService;
import com.thuctap.busbooking.service.impl.CloudinaryService;
import com.thuctap.busbooking.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@MultipartConfig
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserServiceImpl userService;
    CloudinaryService cloudinaryService;
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

    @PostMapping("/complete-registration")
    ApiResponse<UserCreationResponse> createUser(@RequestBody UserCreationRequest request){
        User user = userService.createUserLogin(request);
        UserCreationResponse userCreationResponse = UserCreationResponse.builder()
                .name(user.getName())
                .build();
        return ApiResponse.<UserCreationResponse>builder()
                .code(200)
                .message("Account created successfully")
                .result(userCreationResponse)
                .build();
    }

    @PostMapping("/create-user")
    ApiResponse<UserCreationResponse> createUser1(@RequestBody UserCreationRequest request){
        User user = userService.createUserLogin(request);
        UserCreationResponse userCreationResponse = UserCreationResponse.builder()
                .name(user.getName())
                .build();
        return ApiResponse.<UserCreationResponse>builder()
                .code(200)
                .message("Account created successfully")
                .result(userCreationResponse)
                .build();
    }

    @PostMapping(value = "/create-driver" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<UserCreationResponse> createDriver(@ModelAttribute  DriverCreationRequest request){
        User user = userService.createDriver(request);
        UserCreationResponse userCreationResponse = UserCreationResponse.builder()
                .name("12")
                .build();
        return ApiResponse.<UserCreationResponse>builder()
                .code(200)
                .message("Account created successfully")
                .result(userCreationResponse)
                .build();
    }

    @PostMapping("/create-photo")
    ApiResponse createDriver1(@ModelAttribute Image request){
        System.out.println(">>> File nhận được: " + request.getFile().getOriginalFilename());
        try {
            cloudinaryService.uploadFile(request.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ApiResponse.<UserCreationResponse>builder()
                .code(200)
                .message("Account created successfully")
                .build();
    }

    @GetMapping("/myinfo")
    ApiResponse<User> getMyInfo(){
        return ApiResponse.<User>builder()
                .result(userService.getMyInfo())
                .message("Account get account success !")
                .build();
    }
    @GetMapping("/myinfouser")
    ApiResponse<UserInfoResponse> getMyInfoUser(){
        User user = userService.getMyInfo();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .cccd(user.getCccd())
                .build();
        return ApiResponse.<UserInfoResponse>builder()
                .code(1000)
                .result(userInfoResponse)
                .message("Account get account success !")
                .build();
    }

    @PostMapping(value = "/update-user-info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<UserInfoResponse> updateMyInfoUser(@ModelAttribute  UserUpdateInfoRequest request){
        User user = userService.updateUserInfo(request);
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .cccd(user.getCccd())
                .build();
        return ApiResponse.<UserInfoResponse>builder()
                .code(1000)
                .result(userInfoResponse)
                .message("Account get account success !")
                .build();
    }
}
