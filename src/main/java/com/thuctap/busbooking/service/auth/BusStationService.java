package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.response.BusStationAddResponse;
import com.thuctap.busbooking.dto.response.BusStationUpdateResponse;
import com.thuctap.busbooking.entity.BusStation;

import java.util.List;

public interface BusStationService {
    public List<BusStation> getAllBusSTT();
    public Boolean updateBusStation(BusStationUpdateResponse request);
    public BusStation addBusStation(BusStationAddResponse request);
}
