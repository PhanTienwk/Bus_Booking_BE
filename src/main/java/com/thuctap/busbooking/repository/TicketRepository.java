package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByInvoiceId(int invoiceId);
    Ticket findBySeatPositionIdAndBusTripId(int seatPositionId, int busTripId);
    boolean existsBySeatPositionIdAndBusTripId(int seatPositionId, int busTripId);

    Ticket findById(int integer);

    List<Ticket> findByInvoiceIdIn(List<Integer> invoiceIds);
}
