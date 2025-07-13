package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.response.BusStationAddResponse;
import com.thuctap.busbooking.dto.response.BusStationUpdateResponse;
import com.thuctap.busbooking.entity.BusStation;

import java.util.List;

public interface BusStationService {
    public List<BusStation> getAllBusSTT();
    public Boolean updateBusStation(BusStationUpdateResponse request);
    public BusStation addBusStation(BusStationAddResponse request);
    public List<BusStation> filterBusStations(Integer id, String name, String address, String phone, Integer provinceId, Integer status);
    public Boolean updateBusStationStatus(Integer id, Integer status);
}
