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
@Table(name = "bustype")
public class BusType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int seatCount;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
