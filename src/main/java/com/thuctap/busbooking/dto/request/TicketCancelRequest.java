package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketCancelRequest {
    private String name;
    private String phone;
    private String email;
    private Integer status;
    private String seatName;
    private String bankAccountNumber;
    private Double minAmount;
    private Double maxAmount;
    private String startTime;
    private String endTime;
}
