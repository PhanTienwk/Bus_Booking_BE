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
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @ManyToOne
    @JoinColumn(name = "idBusType")
    BusType busType;

    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
