package com.thuctap.busbooking.dto.response;

import com.thuctap.busbooking.entity.Bus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatPositionResponse {
    int id;
    String name;
    Bus bus;
    boolean status;
}
