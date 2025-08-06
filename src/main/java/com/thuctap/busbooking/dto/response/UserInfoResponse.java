package com.thuctap.busbooking.dto.response;

import com.thuctap.busbooking.entity.Account;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoResponse {
    int id;
    String name;
    String cccd;
    String avatar;
    String phone;
    int gender;
    LocalDate birthDate;
}
