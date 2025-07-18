package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusTripFilterRequest {
    Integer id;
    Integer busRouteId;
    LocalDateTime departureTime;
    Integer busId;
    Integer driverId;
    Integer status;
}