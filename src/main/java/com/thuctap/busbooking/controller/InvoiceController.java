package com.thuctap.busbooking.controller;


import com.cloudinary.Api;
import com.thuctap.busbooking.dto.request.BankDetailRequest;
import com.thuctap.busbooking.dto.request.ExpireInvoiceRequest;
import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.TicketRepository;
import com.thuctap.busbooking.service.auth.InvoiceService;
import com.thuctap.busbooking.service.auth.SeatPositionService;
import com.thuctap.busbooking.service.auth.TicketService;
import com.thuctap.busbooking.service.impl.InvoiceServiceImpl;
import com.thuctap.busbooking.service.impl.UserServiceImpl;
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
public class InvoiceController {

    InvoiceService invoiceService;
    SeatPositionService seatPositionService;
    TicketService ticketService;

    @GetMapping("/list-invoice")
    ApiResponse<List<Invoice>> getAllInvoices() {
        return ApiResponse.<List<Invoice>>builder()
                .result(invoiceService.getAllInvoices())
                .message("Lấy danh sách hoá đơn thành công")
                .build();
    }

    @PostMapping("/create-invoice")
    ApiResponse createInvoice(@RequestBody InvoiceCreationRequest request){
        Invoice invoice = invoiceService.createInvoice(request);
        return ApiResponse.builder()
                .result(invoice.getId())
                .build();
    }

    @GetMapping("/get-invoice-by-userid")
    public ApiResponse<List<Invoice>> getInvoiceByUserId(@RequestParam int id) {
        return ApiResponse.<List<Invoice>>builder()
                .result(invoiceService.getInvoiceByUserId(id))
                .message("Lấy danh sách hóa đơn thành công")
                .build();
    }

    @GetMapping("/get-invoice-by-id/{id}")
    public ApiResponse<List<Ticket>> getInvoiceById(@PathVariable int id) {
        return ApiResponse.<List<Ticket>>builder()
                .result(ticketService.getAllTicketsID(id))
                .build();
    }



    @PutMapping("/update-invoice-status")
    public ApiResponse<Boolean> updateInvoice(@RequestParam Integer id, @RequestParam Integer status) {
        return ApiResponse.<Boolean>builder()
                .result(invoiceService.updateInvoiceStatus(id, status))
                .message("Cập nhật trạng thái bến xe thành công")
                .build();
    }

    @PostMapping("/add-bank-detail")
    ApiResponse createbankdetail(@RequestBody BankDetailRequest request) {

        Boolean result = invoiceService.addBankDetail(request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Thêm thông tin tài khoản ngân hàng thành công")
                .build();
    }
    @PutMapping("/mark-invoice-paid/{invoiceId}")
    public ApiResponse markInvoiceAsPaid(@PathVariable Integer invoiceId) {
        invoiceService.updateInvoiceStatus(invoiceId, 2);
        return ApiResponse.builder()
                .message("Cập nhật trạng thái hóa đơn đã thanh toán thành công")
                .build();
    }

    @PutMapping("/mark-invoice-expired")
    public ApiResponse markInvoiceAsExpired(@RequestBody ExpireInvoiceRequest request) {
        invoiceService.updateInvoiceStatus(request.getInvoiceId(), 0);

        for (String seatName : request.getSelectedSeats()) {
            seatPositionService.updateSeatPosition(seatName, request.getBusId(), true);
        }

        return ApiResponse.builder()
                .message("Cập nhật trạng thái hóa đơn hết hạn và ghế thành công")

                .build();
    }
}
