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
@Table(name = "busstations")
public class BusStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;
    String phone;
    @ManyToOne
    @JoinColumn(name = "idProvince")
    Province province;
    int status;
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
    @OneToMany(mappedBy = "busStationFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusRoute> busRoutesFrom = new ArrayList<>();

    @OneToMany(mappedBy = "busStationTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusRoute> busRoutesTo = new ArrayList<>();

    @OneToMany(mappedBy = "busStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Route> routes = new ArrayList<>();
}
