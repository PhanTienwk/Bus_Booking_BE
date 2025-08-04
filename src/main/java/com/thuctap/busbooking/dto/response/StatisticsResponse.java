package com.thuctap.busbooking.dto.response;

import com.thuctap.busbooking.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticsResponse {
    private long totalBus;
    private long totalBusRoute;
    private long totalBusTrip;
    private long totalBusStation;
}
