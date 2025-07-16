package com.thuctap.busbooking;

import com.thuctap.busbooking.dto.request.UserFilterRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class BusBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusBookingApplication.class, args);
    }
}
