package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.service.auth.InvoiceService;
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
                .build();
    }
}
