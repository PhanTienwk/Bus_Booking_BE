package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.response.SeatPositionResponse;
import com.thuctap.busbooking.entity.SeatPosition;

import java.util.List;

public interface SeatPositionService {
    List<SeatPosition> getSeatsByBusId(int busId);
    void updateSeatPosition(String name,int idBus, boolean status);
}
