package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.BusFilterRequest;
import com.thuctap.busbooking.dto.request.BusRequest;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusType;

import java.util.List;

public interface BusService {
    public List<Bus> getAllBus();
    public List<BusType> getAllBusType();
    Bus addBus(BusRequest busRequest);
    Bus updateBus(int id, BusRequest busRequest);
    Bus updateBusStatus(int id, int status);
    public List<Bus> filterBuses(BusFilterRequest filterRequest);
}
