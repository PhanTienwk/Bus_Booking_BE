package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.BusType;
import com.thuctap.busbooking.repository.BusRepository;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.BusTypeRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusServiceImpl implements BusService {

    BusRepository BusRepo;
    BusTypeRepository BusTypeRepo;
    public List<Bus> getAllBus() {
        return BusRepo.findAll();
    }

    public List<BusType> getAllBusType() {
        return BusTypeRepo.findAll();
    }
}
