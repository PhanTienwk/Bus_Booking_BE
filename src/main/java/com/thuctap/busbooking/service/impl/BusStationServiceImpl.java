package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.BusStationRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusStationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusStationServiceImpl implements BusStationService {

    BusStationRepository BusSTTRepo;

    public List<BusStation> getAllBusSTT() {
        return BusSTTRepo.findAll();
    }
}
