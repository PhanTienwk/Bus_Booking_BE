package com.thuctap.busbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
    int price;
    @ManyToOne
    @JoinColumn(name = "idBus")
    Bus bus;
    @ManyToOne
    @JoinColumn(name = "idDriver")
    User driver;
    int status;
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
