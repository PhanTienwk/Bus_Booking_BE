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
@Table(name = "seatposition")
public class SeatPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @ManyToOne
    @JoinColumn(name = "idBus")
    Bus bus;
    boolean status;
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
