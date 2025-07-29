package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.entity.User;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsID(int id);
    Ticket createTicket(Invoice invoice, SeatPosition seatPosition);
}
