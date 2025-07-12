package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusStationFilterRequest {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Integer provinceId;
    private Integer status;
}
