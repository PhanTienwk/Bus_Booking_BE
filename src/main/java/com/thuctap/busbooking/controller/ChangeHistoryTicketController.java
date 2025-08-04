package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.ChangeTicketRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.ChangeHistoryTicket;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import com.thuctap.busbooking.service.auth.ChangeHistoryTicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeHistoryTicketController {

    ChangeHistoryTicketService changeHistoryTicketService;
    @PostMapping("/createTicket")
    ApiResponse createChangeTicket(@RequestBody ChangeTicketRequest request){
        changeHistoryTicketService.createChangeTicket(request);
        return ApiResponse.builder()
                .message("Successfully!")
                .build();
    }
}
