package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.repository.ProvinceRepository;
import com.thuctap.busbooking.repository.TicketRepository;
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

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }
}
