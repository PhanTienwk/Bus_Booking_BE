package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.SeatPositionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatPositionServiceImpl implements SeatPositionService {
    SeatPositionRepository seatPositionRepository;
    public List<SeatPosition> getSeatsByBusId(int busId) {
        return seatPositionRepository.findByBusId(busId);
    }

    public void updateSeatPosition(String name,int idBus, boolean status) {
        SeatPosition seatPosition = seatPositionRepository.findByNameAndBusId(name,idBus);
        if(seatPosition==null) return;
        seatPosition.setStatus(status);
        seatPositionRepository.save(seatPosition);
    }
}
