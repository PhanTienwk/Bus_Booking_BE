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
@Table(name = "busroute")
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "idBusStationFrom")
    BusStation busStationFrom;

    @ManyToOne
    @JoinColumn(name = "idBusStationTo")
    BusStation busStationTo;

    float distance;
    float travelTime;
    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
