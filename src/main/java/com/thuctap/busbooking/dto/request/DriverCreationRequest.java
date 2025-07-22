package com.thuctap.busbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.thuctap.busbooking.SpecificationQuery.CustomLocalDateTimeDeserializer;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverCreationRequest {
    String email;
    String name;
    MultipartFile file;
    String cccd;
    String phone;
    String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    LocalDate birthDate;
    String password;
}
