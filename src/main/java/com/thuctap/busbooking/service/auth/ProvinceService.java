package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.repository.BusStationRepository;

import java.util.List;

public interface ProvinceService {

    public List<Province> getAllProvince();
}
