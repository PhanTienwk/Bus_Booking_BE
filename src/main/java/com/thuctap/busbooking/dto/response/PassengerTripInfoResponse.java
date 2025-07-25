package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerTripInfoResponse {
    String seatName;
    boolean isBooked;
    String name;
    String phone;
    String pickupPoint;
    String dropOffPoint;
}
