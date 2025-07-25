package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByInvoiceId(int invoiceId);
    List<Ticket> findByInvoiceIdIn(List<Integer> invoiceIds);
}
