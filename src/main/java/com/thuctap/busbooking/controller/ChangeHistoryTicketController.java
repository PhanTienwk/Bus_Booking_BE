package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.ChangeTicketRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.ChangeHistoryTicket;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import com.thuctap.busbooking.service.auth.ChangeHistoryTicketService;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/tickets/{ticketId}/change")
    public ApiResponse changeTicket(@PathVariable int ticketId, @RequestBody ChangeTicketRequest request) {
        if (ticketId != request.getTicketId()) {
            return ApiResponse.builder()
                    .code(400)
                    .message("Mã vé không khớp!")
                    .build();
        }
        try {
            ChangeHistoryTicket changeHistory = changeHistoryTicketService.createChangeTicket(request);
            return ApiResponse.builder()
                    .message("Đổi vé thành công!")
                    .result(changeHistory)
                    .build();
        } catch (Exception e) {
            log.error("Lỗi khi đổi vé: ", e);
            return ApiResponse.builder()
                    .code(500)
                    .message("Lỗi server khi đổi vé!")
                    .build();
        }
    }
}
