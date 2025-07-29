package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Route;
import com.thuctap.busbooking.repository.RouteRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.RouteService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteServiceImpl implements RouteService {
    RouteRepository routeRepository;

    public Route addRoute(BusRoute busRoute, BusStation busStation, int routeLocation, int status){
        Route route = Route.builder()
                .busRoute(busRoute)
                .busStation(busStation)
                .routeLocation(routeLocation)
                .status(status)
                .build();
        return routeRepository.save(route);
    }
    public List<Route> getListRoute(int busRoute){
        return routeRepository.findAllByBusRouteId(busRoute);
    }


}
