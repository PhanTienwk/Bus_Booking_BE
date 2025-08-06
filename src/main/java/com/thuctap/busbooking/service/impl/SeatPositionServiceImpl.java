package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.response.SeatPositionResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.SeatPositionRepository;
import com.thuctap.busbooking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.SeatPositionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatPositionServiceImpl implements SeatPositionService {
    SeatPositionRepository seatPositionRepository;
    TicketRepository ticketRepository;
    BusTripRepository busTripRepository;
    public List<SeatPosition> getSeatsByBusId(int tripId) {
        BusTrip busTrip = busTripRepository.findById(tripId);
        List<SeatPosition> seatPositionList = seatPositionRepository.findByBusId(busTrip.getBus().getId());
        for(SeatPosition seatPosition : seatPositionList){
            if(ticketRepository.existsBySeatPositionIdAndBusTripId(seatPosition.getId(),busTrip.getId())){
                System.out.println(seatPosition.getId()+"-"+busTrip.getId());
                Ticket ticket = ticketRepository.findBySeatPositionIdAndBusTripId(seatPosition.getId(),busTrip.getId());
                if(ticket.getStatus()==1 || ticket.getStatus()==2 || ticket.getStatus()==3) seatPosition.setStatus(false);
            }
        }
        return seatPositionList;
    }

    public void updateSeatPosition(String name,int idBus, boolean status) {
        SeatPosition seatPosition = seatPositionRepository.findByNameAndBusId(name,idBus);
        if(seatPosition==null) return;
        seatPosition.setStatus(status);
        seatPositionRepository.save(seatPosition);
    }
}
