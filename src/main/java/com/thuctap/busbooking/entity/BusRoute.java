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
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
