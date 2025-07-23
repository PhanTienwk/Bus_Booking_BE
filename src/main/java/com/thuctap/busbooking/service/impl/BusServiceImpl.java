package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterBus;
import com.thuctap.busbooking.dto.request.BusFilterRequest;
import com.thuctap.busbooking.dto.request.BusRequest;
import com.thuctap.busbooking.dto.request.BusTripFilterRequest;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.repository.BusRepository;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.BusTypeRepository;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.thuctap.busbooking.service.auth.BusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusServiceImpl implements BusService {

    SeatPositionRepository seatPositionRepo;
    BusRepository busRepo;
    BusTypeRepository busTypeRepo;
    public List<Bus> getAllBus() {
        return busRepo.findAll();
    }

    public List<BusType> getAllBusType() {
        return busTypeRepo.findAll();
    }



    @Transactional(rollbackOn = {Exception.class})
    public Bus addBus(BusRequest busRequest) {
        // Kiểm tra các trường bắt buộc
        if (busRequest.getNameAdd() == null || busRequest.getNameAdd().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên xe là bắt buộc");
        }

        // Tìm loại xe
        BusType busType = busTypeRepo.findById(busRequest.getBusTypeIdAdd())
                .orElseThrow(() -> new RuntimeException("Loại xe không tồn tại với id: " + busRequest.getBusTypeIdAdd()));

        // Kiểm tra seatCount hợp lệ
        int seatCount = busType.getSeatCount();
        if (seatCount <= 0 || seatCount % 2 != 0) {
            throw new IllegalArgumentException("Số ghế không hợp lệ cho loại xe: " + seatCount);
        }

        // Tạo và lưu xe
        Bus bus = Bus.builder()
                .name(busRequest.getNameAdd())
                .busType(busType)
                .status(busRequest.getStatusAdd())
                .build();
        Bus savedBus = busRepo.save(bus);

        // Xóa các ghế hiện có của xe (nếu có)
        seatPositionRepo.deleteByBusId(savedBus.getId());

        // Tạo và lưu từng ghế riêng lẻ cho xe
        int maxSeats = seatCount / 2; // Chia đôi cho hai dãy A và B
        for (int i = 1; i <= maxSeats; i++) {
            // Tạo ghế A (A01-AXX)
            String seatA = String.format("A%02d", i);
            SeatPosition seatPositionA = SeatPosition.builder()
                    .name(seatA)
                    .status(true) // 1 = trống
                    .bus(savedBus)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            try {
                seatPositionRepo.save(seatPositionA);
                log.info("Lưu ghế {} cho xe idBus = {} thành công", seatA, savedBus.getId());
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi lưu ghế " + seatA + " cho xe idBus = " + savedBus.getId() + ": " + e.getMessage());
            }

            // Tạo ghế B (B01-BXX)
            String seatB = String.format("B%02d", i);
            SeatPosition seatPositionB = SeatPosition.builder()
                    .name(seatB)
                    .status(true) // 1 = trống
                    .bus(savedBus)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            try {
                seatPositionRepo.save(seatPositionB);
                log.info("Lưu ghế {} cho xe idBus = {} thành công", seatB, savedBus.getId());
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi lưu ghế " + seatB + " cho xe idBus = " + savedBus.getId() + ": " + e.getMessage());
            }
        }

        return savedBus;
    }

    public Bus updateBus(int id, BusRequest busRequest) {
        Optional<Bus> existingBus = busRepo.findById(id);
        if (existingBus.isEmpty()) {
            throw new RuntimeException("Xe không tồn tại");
        }
        Optional<BusType> busType = busTypeRepo.findById(busRequest.getBusTypeIdAdd());
        if (busType.isEmpty()) {
            throw new RuntimeException("Loại xe không tồn tại");
        }



        Bus bus = existingBus.get();
        bus.setName(busRequest.getNameAdd());
        bus.setBusType(busType.get());
        bus.setStatus(busRequest.getStatusAdd());
        return busRepo.save(bus);
    }


    public Bus updateBusStatus(int id, int status) {

        Bus bus = busRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + id));


        bus.setStatus(status);
        return busRepo.save(bus);
    }

    public List<Bus> filterBuses(BusFilterRequest filterRequest) {
        return busRepo.findAll(FilterBus.filterBus(
                filterRequest.getId(),
                filterRequest.getNameAdd(),
                filterRequest.getBusTypeIdAdd(),
                filterRequest.getStatusAdd()
        ));
    }



}
