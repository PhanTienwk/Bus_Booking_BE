package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterBus;
import com.thuctap.busbooking.dto.request.BusFilterRequest;
import com.thuctap.busbooking.dto.request.BusRequest;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.BusType;
import com.thuctap.busbooking.repository.BusRepository;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.BusTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusServiceImpl implements BusService {

    BusRepository busRepo;
    BusTypeRepository busTypeRepo;
    public List<Bus> getAllBus() {
        return busRepo.findAll();
    }


    public List<BusType> getAllBusType() {
        return busTypeRepo.findAll();
    }



    public Bus addBus(BusRequest busRequest) {
        Optional<BusType> busType = busTypeRepo.findById(busRequest.getBusTypeIdAdd());
        if (busType.isEmpty()) {
            throw new RuntimeException("Loại xe không tồn tại");
        }
        Bus bus = new Bus();
        bus.setName(busRequest.getNameAdd());
        bus.setBusType(busType.get());
        bus.setStatus(busRequest.getStatusAdd());
        return busRepo.save(bus);
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
