package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterUser;
import com.thuctap.busbooking.dto.response.DriverScheduleResponse;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.DriverRepository;
import com.thuctap.busbooking.service.auth.DriverService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;

    @Override
    public List<User> getAllDrivers() {
        return driverRepository.findAllDrivers();
    }

    public List<User> filterDrivers(String name, Integer gender, LocalDateTime birthday, String phone, String email, Integer status, Integer roleId) {
        Specification<User> spec = FilterUser.filterUsers(name, gender, birthday, phone, email, status, roleId);
        return driverRepository.findAll(spec);
    }

    public List<DriverScheduleResponse> getScheduleByDriverAndDateRange(Integer driverId, LocalDateTime start, LocalDateTime end) {
        return driverRepository.getScheduleByDriverAndDateRange(driverId, start, end);
    }

}
