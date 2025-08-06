package com.thuctap.busbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusTripRequest {
    Integer busRouteId;
    LocalDateTime departureTime;
    Float costOperating;
    Float costIncurred;
    Integer price;
    Integer busId;
    Integer driverId;
    Integer status;
}