package com.thuctap.busbooking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BusRouteRequest {
    private Integer busStationFromId;
    private Integer busStationToId;
    private Float distance;
    private Float travelTime;
}
