package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.ChangeHistoryTicket;

@Repository
public interface ChangeHistoryTicketRepository extends JpaRepository<ChangeHistoryTicket, Integer> {}
