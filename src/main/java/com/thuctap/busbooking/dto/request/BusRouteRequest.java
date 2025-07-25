package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusRouteRequest {
    private Integer busStationFromId;
    private Integer busStationToId;
    private Float distance;
    private Float travelTime;
    private List<Integer> listRoute;
}
