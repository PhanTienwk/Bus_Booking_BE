package com.thuctap.busbooking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String phone;
    private Integer gender;
    private LocalDate birthDate;
}
