package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceCreationRequest {
    int id;
    String email;
    String name;
    String phone;
    int number_of_tickets;
    int payment_method;
    List<String> listidseatposition;
    int idbustrip;
}
