package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Otp o WHERE o.expiresAt < :currentTime")
    void deleteExpiredOtp(LocalDateTime currentTime);
}