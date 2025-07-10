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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String email;
    String password;

    @ManyToOne
    @JoinColumn(name = "role")
    Role role;

    int status;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
