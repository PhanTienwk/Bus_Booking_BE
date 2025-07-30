package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpireInvoiceRequest {
    private Integer invoiceId;
    private List<String> selectedSeats;
    private Integer busId;
}
