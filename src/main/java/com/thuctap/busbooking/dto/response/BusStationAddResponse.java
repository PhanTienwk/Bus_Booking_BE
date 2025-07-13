package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusStationAddResponse {
    int provinceIdAdd;

    String nameAdd;
    String addressAdd;
    String phoneAdd;

    int statusAdd;

}