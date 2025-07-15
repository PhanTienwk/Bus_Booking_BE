package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusFilterRequest {
    private Integer id; // Thêm trường id để lọc
    private Integer busTypeIdAdd;
    private String nameAdd;
    private Integer statusAdd;
}
