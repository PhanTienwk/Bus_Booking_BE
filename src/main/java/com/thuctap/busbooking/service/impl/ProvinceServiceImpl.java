package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.ProvinceRequest;
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


    public ProvinceRequest addProvince(ProvinceRequest dto) {
        if (provinceRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Tên tỉnh/thành phố đã tồn tại!");
        }
        Province province = Province.builder()
                .name(dto.getName())
                .status(dto.getStatus())
                .build();
        province = provinceRepository.save(province);
        return convertToDTO(province);
    }


    public ProvinceRequest updateProvince(int id, ProvinceRequest dto) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tỉnh/thành phố không tồn tại"));
        if (!province.getName().equals(dto.getName()) && provinceRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Tên tỉnh/thành phố đã tồn tại!");
        }
        province.setName(dto.getName());
        province.setStatus(dto.getStatus());
        province = provinceRepository.save(province);
        return convertToDTO(province);
    }


    public void updateProvinceStatus(int id, int status) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tỉnh/thành phố không tồn tại"));
        province.setStatus(status);
        provinceRepository.save(province);
    }

    private ProvinceRequest convertToDTO(Province province) {
        ProvinceRequest dto = new ProvinceRequest();
        dto.setId(province.getId());
        dto.setName(province.getName());
        dto.setStatus(province.getStatus());
        return dto;
    }
}
