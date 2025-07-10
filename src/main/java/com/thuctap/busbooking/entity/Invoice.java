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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime timeOfBooking;
    int numberOfTickets;
    float totalAmount;

    @ManyToOne
    @JoinColumn(name = "idBusTrip")
    BusTrip busTrip;

    String name;
    String phone;
    String email;

    @ManyToOne
    @JoinColumn(name = "idUser")
    User user;

    int paymentMethod;
    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
