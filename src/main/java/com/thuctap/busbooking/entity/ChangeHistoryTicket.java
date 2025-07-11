package com.thuctap.busbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "changehistoryticket")
public class ChangeHistoryTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @CreatedDate
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
