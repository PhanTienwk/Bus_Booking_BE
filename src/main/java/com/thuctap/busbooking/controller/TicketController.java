package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.TicketConsultRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.TicketConsultResponse;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.exception.AppException;
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

    @GetMapping("/get-ticket-by-idUser")
    public ApiResponse<List<Ticket>> getTicketByIdUser(@RequestParam int id) {
        return ApiResponse.<List<Ticket>>builder()
                .result(ticketService.getTicketByUserId(id))
                .message("lấy danh sách vé thành công")
                .build();
    }

    @PutMapping("/update-ticket-status")
    public ApiResponse<Boolean> updateTicketStatus(@RequestParam Integer id, @RequestParam Integer status) {
        return ApiResponse.<Boolean>builder()
                .result(ticketService.updateTicketStatus(id, status))
                .message("Cập nhật trạng thái bến xe thành công")
                .build();
    }

    @PostMapping("/consultTicket")
    public ApiResponse<TicketConsultResponse> getTicketConsult(@RequestBody TicketConsultRequest request){
        TicketConsultResponse ticketConsultResponse = ticketService.getTicketConsult(request);
        if(ticketConsultResponse.getNameUser().isEmpty()){
            return ApiResponse.<TicketConsultResponse>builder()
                    .code(1031)
                    .build();
        }
        return ApiResponse.<TicketConsultResponse>builder()
                .result(ticketConsultResponse)
                .build();

    }

}
