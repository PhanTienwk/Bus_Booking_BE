package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.response.DriverScheduleResponse;
import com.thuctap.busbooking.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface DriverService {
    List<User> getAllDrivers();
    public List<User> filterDrivers(String name, Integer gender, LocalDateTime birthday, String phone, String email, Integer status, Integer role);
    List<DriverScheduleResponse> getScheduleByDriverAndDateRange(Integer driverId, LocalDateTime startDate, LocalDateTime endDate);
}
