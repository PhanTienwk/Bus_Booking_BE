package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDetailRequest {
    private Integer idUser;
    private Integer idInvoice;
    private String BankName;
    private String BankAccount;
}
