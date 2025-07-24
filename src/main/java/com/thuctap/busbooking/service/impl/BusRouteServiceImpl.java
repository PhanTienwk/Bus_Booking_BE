package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.BusRouteRequest;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.repository.BusRouteRepository;
import com.thuctap.busbooking.repository.BusStationRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.service.auth.RouteService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.thuctap.busbooking.SpecificationQuery.FilterBusRoute;
import com.thuctap.busbooking.service.auth.BusRouteService;

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
public class BusRouteServiceImpl implements BusRouteService {

    BusRouteRepository busRouteRepository;
    BusStationRepository busStationRepository;
    RouteService routeService;

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }

    @Override
    public BusRoute updateBusRoute(int id, BusRouteRequest request) {
        BusRoute existingRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe"));

        if (request.getBusStationFromId() != null) {
            BusStation fromStation = busStationRepository.findById(request.getBusStationFromId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bến đi"));
            existingRoute.setBusStationFrom(fromStation);
        }

        if (request.getBusStationToId() != null) {
            BusStation toStation = busStationRepository.findById(request.getBusStationToId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bến đến"));
            existingRoute.setBusStationTo(toStation);
        }

        if (request.getDistance() != null) {
            existingRoute.setDistance(request.getDistance());
        }

        if (request.getTravelTime() != null) {
            existingRoute.setTravelTime(request.getTravelTime());
        }

        return busRouteRepository.save(existingRoute);
    }

    @Override
    @Transactional
    public void deleteBusRoute(int id) {
        BusRoute busRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe"));

        busRoute.setStatus(0);
        busRouteRepository.save(busRoute);
    }

    @Override
    @Transactional
    public void restoreBusRoute(int id) {
        BusRoute busRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe"));

        busRoute.setStatus(1);
        busRouteRepository.save(busRoute);
    }

    @Transactional
    public BusRoute addBusRoute(BusRouteRequest request) {
        BusStation from = busStationRepository.findById(request.getBusStationFromId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bến đi với ID: " + request.getBusStationFromId()));

        BusStation to = busStationRepository.findById(request.getBusStationToId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bến đến với ID: " + request.getBusStationToId()));
        BusRoute route = BusRoute.builder()
                .busStationFrom(from)
                .busStationTo(to)
                .distance(request.getDistance())
                .travelTime(request.getTravelTime())
                .status(1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        routeService.addRoute(route,from,1,1);
        int k = 2;
        for(int i : request.getListRoute()){
            BusStation busStation = busStationRepository.findById(i)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bến đến với ID: " + i));
            routeService.addRoute(route,busStation,k,1);
            ++k;
        }
        routeService.addRoute(route,to,k,1);
        return busRouteRepository.save(route);
    }

    public List<BusRoute> filterBusRoutes(String from, String to, Float distance, Float travelTime, Integer status) {
        Specification<BusRoute> spec = FilterBusRoute.filterBusRoutes(from, to, distance, travelTime, status);
        return busRouteRepository.findAll(spec);
    }

}
