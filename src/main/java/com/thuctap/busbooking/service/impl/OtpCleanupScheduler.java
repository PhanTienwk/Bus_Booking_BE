package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OtpCleanupScheduler {

    @Autowired
    private OtpRepository otpRepository;

    @Scheduled(fixedRate = 60000)
    public void cleanExpiredOtp() {
        otpRepository.deleteExpiredOtp(LocalDateTime.now());
    }
}