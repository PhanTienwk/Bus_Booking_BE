package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.ProvinceRequest;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Province;
import com.thuctap.busbooking.repository.BusStationRepository;

import java.util.List;

public interface ProvinceService {
    public List<Province> getAllProvinces();

    public List<Province> getAllProvince();


    ProvinceRequest addProvince(ProvinceRequest dto);
    ProvinceRequest updateProvince(int id, ProvinceRequest dto);
    void updateProvinceStatus(int id, int status);
}
