package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.repository.UserRepository;
import com.thuctap.busbooking.service.auth.SeatPositionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.InvoiceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {

    InvoiceRepository invoiceRepository;

    BusTripRepository busTripRepository;
    SeatPositionService seatPositionService;


    UserRepository userRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }


    @Transactional
    public Invoice createInvoice(InvoiceCreationRequest request){
        BusTrip busTrip = busTripRepository.findAllById(request.getId());
        for(String name : request.getListidseatposition()){
            System.out.println(busTrip.getId());
            System.out.println(name);
            seatPositionService.updateSeatPosition(name,request.getIdbustrip(),false);
        }
        Invoice invoice = Invoice.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .numberOfTickets(request.getNumber_of_tickets())
                .paymentMethod(request.getPayment_method())
                .timeOfBooking(LocalDateTime.now())
                .totalAmount(request.getNumber_of_tickets()*busTrip.getPrice())
                .status(1)
                .busTrip(busTrip)
                .build();
        return invoiceRepository.save(invoice);
    };


    public List<Invoice> getInvoiceByUserId(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            log.warn("Không tìm thấy người dùng với số điện thoại: {}", phone);
            return List.of();
        }
        return invoiceRepository.findByUserId(user.getId());
    }

}
