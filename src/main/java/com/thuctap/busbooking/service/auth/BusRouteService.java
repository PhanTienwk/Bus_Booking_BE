package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.BusRouteRequest;
import com.thuctap.busbooking.dto.request.UserRequest;
import com.thuctap.busbooking.dto.response.BusStationAddResponse;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.User;

import java.util.List;

public interface BusRouteService {
    List<BusRoute> getAllBusRoutes();
    void deleteBusRoute(int id);
    void restoreBusRoute(int id);
    BusRoute updateBusRoute(int id, BusRouteRequest request);
    public BusRoute addBusRoute(BusRouteRequest request);
    public List<BusRoute> filterBusRoutes(String from, String to, Float distance, Float travelTime, Integer status);
}
