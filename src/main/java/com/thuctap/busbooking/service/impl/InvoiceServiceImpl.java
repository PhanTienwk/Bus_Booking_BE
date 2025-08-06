package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.BankDetailRequest;
import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.repository.*;
import com.thuctap.busbooking.service.auth.SeatPositionService;
import com.thuctap.busbooking.service.auth.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
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
    SeatPositionRepository seatPositionRepository;
    TicketService ticketService;
    AccountRepository accountRepository;


    UserRepository userRepository;
    BankDTReponsitory bankDTReponsitory;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }


    @Transactional
    public Invoice createInvoice(InvoiceCreationRequest request){
        BusTrip busTrip = busTripRepository.findAllById(request.getId());
        var context = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByEmail(String.valueOf(context)).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        User user = userRepository.findByAccount(account);
        Invoice invoice = Invoice.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .numberOfTickets(request.getNumber_of_tickets())
                .paymentMethod(request.getPayment_method())
                .timeOfBooking(LocalDateTime.now())
                .totalAmount(request.getNumber_of_tickets()*busTrip.getPrice())
                .status(1)
                .user(user)
                .busTrip(busTrip)
                .build();
        invoiceRepository.save(invoice);
        for(String name : request.getListidseatposition()){
            SeatPosition seatPosition = seatPositionRepository.findByNameAndBusId(name,request.getIdbustrip());
            Ticket ticket = ticketService.createTicket(invoice,seatPosition,busTrip);
        }
        return invoice;
    }

    public Invoice getInvoiceId(int id) {
        return invoiceRepository.findById(id);
    }

    ;


    public List<Invoice> getInvoiceByUserId(int id) {
        User user = userRepository.findById(id).orElseThrow(null);
        if (user == null) {
            log.warn("Không tìm thấy người dùng với id user: {}", id);
            return List.of();
        }
        return invoiceRepository.findByUserId(user.getId());
    }


    public Boolean updateInvoiceStatus(Integer id, Integer status) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id: " + id));



        invoice.setStatus(status);
        invoice.setUpdatedAt(LocalDateTime.now());
        return invoiceRepository.save(invoice) != null;
    }


    public Boolean addBankDetail(BankDetailRequest bankDetailRequest) {
        Invoice invoice = invoiceRepository.findById(bankDetailRequest.getIdInvoice())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id: " + bankDetailRequest.getIdInvoice()));

        User user = userRepository.findById(bankDetailRequest.getIdUser())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id: " + bankDetailRequest.getIdUser()));

        BankDetails bankDetails = BankDetails.builder()
                .bankAccountNumber(bankDetailRequest.getBankAccount())
                .bankName(bankDetailRequest.getBankName())
                .user(bankDetailRequest.getIdUser() != null ? userRepository.findById(bankDetailRequest.getIdUser())
                        .orElseThrow(() -> new IllegalArgumentException("User không tồn tại")) : null)
                .invoice(bankDetailRequest.getIdInvoice() != null ? invoiceRepository.findById(bankDetailRequest.getIdInvoice())
                        .orElseThrow(() -> new IllegalArgumentException("Hóa đơn không tồn tại")) : null)
                .build();

        return bankDTReponsitory.save(bankDetails) != null;
    }
    @Override
    public void updateInvoiceStatus(Integer invoiceId, int status) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        invoice.setStatus(status);
        invoiceRepository.save(invoice);

    }

}
