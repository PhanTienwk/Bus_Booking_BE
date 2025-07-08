package com.thuctap.busbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    Driver driver;
    int status;
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
    @OneToMany(mappedBy = "busTrip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChangeHistoryTicket> changeHistoryTickets = new ArrayList<>();

    @OneToMany(mappedBy = "busTrip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices = new ArrayList<>();
}
