package com.thuctap.busbooking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "busstations")
public class BusStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String address;
    String phone;

    @ManyToOne
    @JoinColumn(name = "idProvince")
    Province province;

    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
