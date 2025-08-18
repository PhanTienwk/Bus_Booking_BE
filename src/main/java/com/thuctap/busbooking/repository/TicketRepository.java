package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> , JpaSpecificationExecutor<Ticket> {
    List<Ticket> findByInvoiceId(int invoiceId);
    Ticket findTop1BySeatPositionIdAndBusTripIdOrderByIdDesc(int seatPositionId, int busTripId);
    boolean existsBySeatPositionIdAndBusTripId(int seatPositionId, int busTripId);

    Ticket findById(int integer);

    List<Ticket> findByInvoiceIdIn(List<Integer> invoiceIds);


    @Query("SELECT t FROM Ticket t " +
            "JOIN t.invoice i " +
            "JOIN i.user u " +
            "JOIN i.busTrip bt " +
            "JOIN bt.busRoute br " +
            "JOIN br.busStationFrom bsFrom " +
            "JOIN br.busStationTo bsTo " +
            "JOIN t.seatPosition sp " +
            "WHERE u.id = :id")
    List<Ticket> findTicketsByUserId(@Param("id") int id);


}
