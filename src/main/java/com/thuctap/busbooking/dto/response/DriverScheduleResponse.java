package com.thuctap.busbooking.dto.response;

import com.thuctap.busbooking.entity.BusType;
import com.thuctap.busbooking.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverScheduleResponse {
    Integer tripId;
    String routeName;
    String departureStationAddress;
    String arrivalStationAddress;
    String licensePlate;
    LocalDateTime departureTime;
    float estimatedHours;
    private BusType busType;
}
