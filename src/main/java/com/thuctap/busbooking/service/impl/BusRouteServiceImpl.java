package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.repository.BusRouteRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusRouteService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusRouteServiceImpl implements BusRouteService {

    BusRouteRepository busRouteRepository;

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }
}
