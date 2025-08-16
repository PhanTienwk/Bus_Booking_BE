package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceConsultResponse {
    String name;
    String email;
    int number_of_tickets;
    int payment_method;
    String phone;
    int status;
    double total_amount;
    String nameStationTo;
    String nameStationFrom;
    LocalDateTime departureTime;
}
