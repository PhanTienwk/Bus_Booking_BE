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
@Table(name = "bustrip")
public class BusTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "idBusRoute")
    BusRoute busRoute;

    LocalDateTime departureTime;
    float costOperating;
    float costIncurred;
    int price;

    @ManyToOne
    @JoinColumn(name = "idBus")
    Bus bus;

    @ManyToOne
    @JoinColumn(name = "idDriver")
    User user;

    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
