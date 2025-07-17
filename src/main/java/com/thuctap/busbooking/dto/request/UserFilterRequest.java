package com.thuctap.busbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFilterRequest {
    private String name;
    private Integer gender;
    private LocalDateTime birthday;
    private String phone;
    private String email;
    private Integer status;
    private Integer roleId;
}
