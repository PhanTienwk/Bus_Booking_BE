package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusTripService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusTripServiceImpl implements BusTripService {

    BusTripRepository busTripRepository;

    @Override
    public List<BusTrip> getAllBusTrips() {
        return busTripRepository.findAll();
    }
}
