package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.repository.ProvinceRepository;
import com.thuctap.busbooking.repository.TicketRepository;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.ProvinceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceServiceImpl implements ProvinceService {

    ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }
}
