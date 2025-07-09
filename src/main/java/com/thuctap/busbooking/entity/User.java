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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String cccd;
    String avatar;
    String phone;
    int gender;
    LocalDateTime birthDate;
    @OneToOne
    @JoinColumn(name = "idAccount")
    Account account;
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
