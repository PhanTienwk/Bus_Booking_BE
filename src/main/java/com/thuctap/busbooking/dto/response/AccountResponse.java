package com.thuctap.busbooking.dto.response;

import com.thuctap.busbooking.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String email;
    Role role;
}
