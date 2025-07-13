package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Ticket;

import java.util.List;

public interface BusTripService {
    List<BusTrip> getAllBusTrips();
}
