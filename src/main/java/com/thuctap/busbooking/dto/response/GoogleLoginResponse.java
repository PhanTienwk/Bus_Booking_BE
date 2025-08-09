package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoogleLoginResponse {
    private boolean isNewUser;
    private String email;
    private String name;
    private String token;
    private String role;
}
