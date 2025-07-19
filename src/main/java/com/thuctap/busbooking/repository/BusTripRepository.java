package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.dto.response.CostSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusTrip;

import java.time.LocalDateTime;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Integer> {
    long countByStatus(int status);

    @Query("SELECT SUM(b.costOperating + b.costIncurred) FROM BusTrip b WHERE b.departureTime BETWEEN :start AND :end")
    Integer sumPriceByDepartureTimeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.costOperating), 0) FROM BusTrip b WHERE b.departureTime BETWEEN :start AND :end")
    Float sumCostOperatingBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.costIncurred), 0) FROM BusTrip b WHERE b.departureTime BETWEEN :start AND :end")
    Float sumCostIncurredBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
