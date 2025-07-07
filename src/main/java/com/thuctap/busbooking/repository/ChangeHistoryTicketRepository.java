package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.ChangeHistoryTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeHistoryTicketRepository extends JpaRepository<ChangeHistoryTicket,Integer> {
}
