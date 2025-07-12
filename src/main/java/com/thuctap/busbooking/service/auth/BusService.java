package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusType;

import java.util.List;

public interface BusService {
    public List<Bus> getAllBus();
    public List<BusType> getAllBusType();
}
