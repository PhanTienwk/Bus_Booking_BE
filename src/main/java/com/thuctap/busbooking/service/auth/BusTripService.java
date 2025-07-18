package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.BusTripRequest;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.User;

import java.util.List;

public interface BusTripService {

    public List<BusTrip> getAllBusTrip();

    public List<BusRoute> getAllBusRoutes();

    public List<User> getAllUsers();
    public Boolean updateBusTripStatus(Integer id, Integer status);
    BusTrip addBusTrip(BusTripRequest request);
    BusTrip updateBusTrip(Integer id, BusTripRequest request);
}
