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
@Table(name = "changehistoryticket")
public class ChangeHistoryTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "changeTime")
    LocalDateTime changeTime;

    @Column(nullable = false)
    float price;

    @ManyToOne
    @JoinColumn(name = "idSeatPositon")
    SeatPosition seatPosition;

    @ManyToOne
    @JoinColumn(name = "idTicket")
    Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "idBusTrip")
    BusTrip busTrip;
}
