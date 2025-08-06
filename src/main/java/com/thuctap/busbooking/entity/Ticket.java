package com.thuctap.busbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "idSeatPosition")
    SeatPosition seatPosition;
    @ManyToOne
    @JoinColumn(name = "idInvoice")
    Invoice invoice;
    @Column(nullable = false)
    int status;
    @ManyToOne
    @JoinColumn(name = "idBusTrip")
    BusTrip busTrip;
    @CreatedDate
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
