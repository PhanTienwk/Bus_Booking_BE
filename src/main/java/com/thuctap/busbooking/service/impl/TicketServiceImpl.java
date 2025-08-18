package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterProvince;
import com.thuctap.busbooking.SpecificationQuery.FilterTicketCancel;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.repository.TicketRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.thuctap.busbooking.service.auth.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllTicketsID(int id) {
        return ticketRepository.findByInvoiceId(id);
    }

    public Ticket createTicket(Invoice invoice, SeatPosition seatPosition, BusTrip busTrip) {
        Ticket ticket = Ticket.builder()
                .status(1)
                .seatPosition(seatPosition)
                .invoice(invoice)
                .busTrip(busTrip)
                .build();
        return ticketRepository.save(ticket);
    }



    public List<Ticket> getTicketByUserId(int id) {
        List<Ticket> tickets = ticketRepository.findTicketsByUserId(id);


       return tickets;

    }

    public Boolean updateTicketStatus(Integer id, Integer status) {
        Ticket ticket= ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vé với id: " + id));



        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket) != null;
    }

    @Override
    public void updateTicketStatusByInvoiceId(Integer invoiceId, int status) {
        List<Ticket> tickets = ticketRepository.findByInvoiceId(invoiceId);
        for (Ticket ticket : tickets) {
            ticket.setStatus(status);
        }
        ticketRepository.saveAll(tickets);
    }

    public List<Ticket> filterTicket(String name,
                                       String phone,
                                       String email,
                                       Integer status,
                                       String seatName,
                                       String bankAccountNumber,
                                       Double minAmount,
                                       Double maxAmount,
                                       String startTime,
                                       String endTime) {
        Specification<Ticket> spec = FilterTicketCancel.filterTickets(name, phone, email, status, seatName, bankAccountNumber, minAmount, maxAmount, startTime, endTime);
        return ticketRepository.findAll(spec);
    }


}
