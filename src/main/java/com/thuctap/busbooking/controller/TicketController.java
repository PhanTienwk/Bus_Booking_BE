package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.service.impl.TicketServiceImpl;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin")
public class TicketController {

    TicketServiceImpl ticketService;

    @GetMapping("/list-ticket")
    ApiResponse<List<Ticket>> getAllTickets() {
        return ApiResponse.<List<Ticket>>builder()
                .result(ticketService.getAllTickets())
                .message("Lấy danh sách vé thành công")
                .build();
    }

    @GetMapping("/list-ticket/{id}")
    ApiResponse<List<Ticket>> getListTicketID(@PathVariable Integer id){
        return ApiResponse.<List<Ticket>>builder()
                .result(ticketService.getAllTicketsID(id))
                .message("Lấy danh sách vé thành công")
                .build();
    }

    @GetMapping("/get-ticket-by-phone")
    public ApiResponse<List<Ticket>> getTicketByPhone(@RequestParam String phone) {
        return ApiResponse.<List<Ticket>>builder()
                .result(ticketService.getTicketByPhone(phone))
                .message("lấy danh sách vé thành công")
                .build();
    }
}
