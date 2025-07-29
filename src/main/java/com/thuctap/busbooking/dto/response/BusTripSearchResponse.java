package com.thuctap.busbooking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusRoute;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusTripSearchResponse {
    int id;
    BusRoute busRoute;
    LocalDateTime departureTime;
    float costOperating ;
    float costIncurred;
    int price;
    int status;
    Bus bus;
    int count;
    int countA;
    int countB;
}
