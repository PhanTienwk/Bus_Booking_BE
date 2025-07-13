package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterBusStation;
import com.thuctap.busbooking.dto.response.BusStationAddResponse;
import com.thuctap.busbooking.dto.response.BusStationUpdateResponse;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.ProvinceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusStationService;

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
public class BusStationServiceImpl implements BusStationService {

    BusStationRepository BusSTTRepo;
    ProvinceRepository ProvinceRepo;
    public List<BusStation> getAllBusSTT() {
        return BusSTTRepo.findAll();
    }


    public Boolean updateBusStation(BusStationUpdateResponse request) {
        boolean result = false;
        BusStation busStation = BusSTTRepo.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Bus station not found with id: " + request.getId()));

        Province province = ProvinceRepo.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found with id: " + request.getProvinceId()));

        busStation.setName(request.getName());
        busStation.setAddress(request.getAddress());
        busStation.setPhone(request.getPhone());
        busStation.setProvince(province);
        busStation.setStatus(request.getStatus());
        busStation.setUpdatedAt(LocalDateTime.now());

        return BusSTTRepo.save(busStation) != null;
    }

    public BusStation addBusStation(BusStationAddResponse request) {
        Province province = ProvinceRepo.findById(request.getProvinceIdAdd())
                .orElseThrow(() -> new RuntimeException("Province not found with id: " + request.getProvinceIdAdd()));

        BusStation busStation = BusStation.builder()
                .name(request.getNameAdd())
                .address(request.getAddressAdd())
                .phone(request.getPhoneAdd())
                .province(province)
                .status(request.getStatusAdd())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return BusSTTRepo.save(busStation);
    }


    public List<BusStation> filterBusStations(Integer id, String name, String address, String phone, Integer provinceId, Integer status) {
        Specification<BusStation> spec = FilterBusStation.filterBusStation(id, name, address, phone, provinceId, status);
        return BusSTTRepo.findAll(spec);
    }
    public Boolean updateBusStationStatus(Integer id, Integer status) {
        BusStation busStation = BusSTTRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bến xe với id: " + id));

        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Trạng thái phải là 0 hoặc 1");
        }

        busStation.setStatus(status);
        busStation.setUpdatedAt(LocalDateTime.now());
        return BusSTTRepo.save(busStation) != null;
    }
}
