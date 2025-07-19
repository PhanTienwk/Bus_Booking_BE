package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.response.CostSummaryResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.SpecificationQuery.FilterBusTrip;
import com.thuctap.busbooking.dto.request.BusTripFilterRequest;
import com.thuctap.busbooking.dto.request.BusTripRequest;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.repository.*;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusTripService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusTripServiceImpl implements BusTripService {

    BusTripRepository busTripRepo;
    BusTypeRepository busTypeRepo;
    BusRepository busRepo;
    BusRouteRepository busRouteRepo;
    UserRepository userRepo;
    public List<BusTrip> getAllBusTrip() {
        return busTripRepo.findAll();
    }

    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepo.findAll();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }



    public Boolean updateBusTripStatus(Integer id, Integer status) {
        BusTrip busTrip = busTripRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bến xe với id: " + id));

        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Trạng thái phải là 0 hoặc 1");
        }

        busTrip.setStatus(status);
        busTrip.setUpdatedAt(LocalDateTime.now());
        return busTripRepo.save(busTrip) != null;
    }

    public BusTrip addBusTrip(BusTripRequest request) {
        // Kiểm tra các trường bắt buộc
        if (request.getBusRouteId() == null || request.getDepartureTime() == null ||
                request.getCostOperating() == null || request.getCostIncurred() == null ||
                request.getPrice() == null || request.getBusId() == null || request.getDriverId() == null) {
            throw new IllegalArgumentException("Vui lòng điền đầy đủ các trường bắt buộc");
        }

        // Tìm các thực thể liên quan
        BusRoute busRoute = busRouteRepo.findById(request.getBusRouteId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe với id: " + request.getBusRouteId()));
        Bus bus = busRepo.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe với id: " + request.getBusId()));
        User driver = userRepo.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế với id: " + request.getDriverId()));

        // Tạo mới BusTrip
        BusTrip busTrip = BusTrip.builder()
                .busRoute(busRoute)
                .departureTime(request.getDepartureTime())
                .costOperating(request.getCostOperating())
                .costIncurred(request.getCostIncurred())
                .price(request.getPrice())
                .bus(bus)
                .user(driver)
                .status(request.getStatus() != null ? request.getStatus() : 1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return busTripRepo.save(busTrip);
    }


    public BusTrip updateBusTrip(Integer id, BusTripRequest request) {
        // Tìm chuyến xe cần cập nhật
        BusTrip busTrip = busTripRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến xe với id: " + id));

        // Kiểm tra các trường bắt buộc
        if (request.getBusRouteId() == null || request.getDepartureTime() == null ||
                request.getCostOperating() == null || request.getCostIncurred() == null ||
                request.getPrice() == null || request.getBusId() == null || request.getDriverId() == null) {
            throw new IllegalArgumentException("Vui lòng điền đầy đủ các trường bắt buộc");
        }

        // Tìm các thực thể liên quan
        BusRoute busRoute = busRouteRepo.findById(request.getBusRouteId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe với id: " + request.getBusRouteId()));
        Bus bus = busRepo.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe với id: " + request.getBusId()));
        User driver = userRepo.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế với id: " + request.getDriverId()));

        // Cập nhật thông tin chuyến xe
        busTrip.setBusRoute(busRoute);
        busTrip.setDepartureTime(request.getDepartureTime());
        busTrip.setCostOperating(request.getCostOperating());
        busTrip.setCostIncurred(request.getCostIncurred());
        busTrip.setPrice(request.getPrice());
        busTrip.setBus(bus);
        busTrip.setUser(driver);
        busTrip.setStatus(request.getStatus() != null ? request.getStatus() : busTrip.getStatus());
        busTrip.setUpdatedAt(LocalDateTime.now());

        return busTripRepo.save(busTrip);
    }

    public List<BusTrip> filterBusTrips(BusTripFilterRequest request) {
        return busTripRepo.findAll(FilterBusTrip.filterBusTrip(
                request.getId(),
                request.getBusRouteId(),
                request.getDepartureTime(),
                request.getBusId(),
                request.getDriverId(),
                request.getStatus()
        ));
    }
}
