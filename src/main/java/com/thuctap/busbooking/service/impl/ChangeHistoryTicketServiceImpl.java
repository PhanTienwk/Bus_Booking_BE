package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.ChangeTicketRequest;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.ChangeHistoryTicket;
import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.ChangeHistoryTicketRepository;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import com.thuctap.busbooking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.ChangeHistoryTicketService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeHistoryTicketServiceImpl implements ChangeHistoryTicketService {

    BusTripRepository busTripRepository;
    SeatPositionRepository seatPositionRepository;
    TicketRepository ticketRepository;
    ChangeHistoryTicketRepository changeHistoryTicketRepository;

    @Transactional
    public ChangeHistoryTicket createChangeTicket(ChangeTicketRequest request) {
        Ticket ticket = ticketRepository.findById(request.getTicketId());
        BusTrip busTrip = busTripRepository.findById(request.getBusTripId());
        SeatPosition seatPositionChange = seatPositionRepository.findById(request.getSeatPosition());
        SeatPosition seatPosition = ticket.getSeatPosition();
        ticket.setStatus(2);
        ticketRepository.save(ticket);
        seatPosition.setStatus(true);
        seatPositionChange.setStatus(false);
        seatPositionRepository.save(seatPosition);
        seatPositionRepository.save(seatPositionChange);
        return changeHistoryTicketRepository.save(ChangeHistoryTicket.builder()
                        .busTrip(busTrip)
                        .seatPosition(seatPositionChange)
                        .ticket(ticket)
                        .changeTime(LocalDateTime.now())
                .build());
    }
}
