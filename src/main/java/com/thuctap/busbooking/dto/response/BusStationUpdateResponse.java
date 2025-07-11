package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusStationUpdateResponse {
    int id;
    String name;
    String address;
    String phone;
    int provinceId;
    int status;
}