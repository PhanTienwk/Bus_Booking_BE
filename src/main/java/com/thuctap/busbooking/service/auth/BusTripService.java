package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.response.BusTripSearchResponse;
import com.thuctap.busbooking.dto.response.CostSummaryResponse;
import com.thuctap.busbooking.dto.request.BusTripFilterRequest;
import com.thuctap.busbooking.dto.request.BusTripRequest;
import com.thuctap.busbooking.dto.response.PassengerTripInfoResponse;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BusTripService {

    public List<BusTrip> getAllBusTrip();

    public List<BusRoute> getAllBusRoutes();

    public List<User> getAllUsers();
    public Boolean updateBusTripStatus(Integer id, Integer status);
    BusTrip addBusTrip(BusTripRequest request);
    BusTrip updateBusTrip(Integer id, BusTripRequest request);
    List<BusTrip> filterBusTrips(BusTripFilterRequest request);
    List<PassengerTripInfoResponse> getPassengerTripInfoByTripId(Integer tripId);
    List<BusTripSearchResponse> getBusTrip(int startStationId, int endStationId, LocalDate date, int count);
}
