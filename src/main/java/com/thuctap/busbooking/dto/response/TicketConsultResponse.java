package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketConsultResponse {
    String nameSeatPosition;
    String nameStationTo;
    String nameStationFrom;
    LocalDateTime departureTime;
    String nameUser;
    String emailUser;
    int status;
}
