package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.repository.TicketRepository;
import org.springframework.stereotype.Service;
import com.thuctap.busbooking.service.auth.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

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
}
