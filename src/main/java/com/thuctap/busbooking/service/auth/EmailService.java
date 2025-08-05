package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailService {
    void sendEmailTicket(BusTrip busTrip, List<Ticket> ticketList,String email) throws MessagingException;
}
