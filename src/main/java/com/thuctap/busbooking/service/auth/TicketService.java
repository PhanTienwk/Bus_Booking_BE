package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.entity.User;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
}
