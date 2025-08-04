package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.dto.response.CostSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.thuctap.busbooking.entity.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusTrip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Integer>,JpaSpecificationExecutor<BusTrip> {
    long countByStatus(int status);

    BusTrip findById(int id);

    BusTrip findAllById(int integers);

    @Query("SELECT SUM(DISTINCT b.costOperating + b.costIncurred) " +
            "FROM BusTrip b " +
            "JOIN Invoice i ON i.busTrip.id = b.id " +
            "WHERE b.departureTime BETWEEN :start AND :end " +
            "AND i.status = 3")
    Integer sumPriceByDepartureTimeBetween(@Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.costOperating), 0) " +
            "FROM BusTrip b " +
            "WHERE b.departureTime BETWEEN :start AND :end " +
            "AND EXISTS (SELECT 1 FROM Invoice i WHERE i.busTrip.id = b.id AND i.status = 3)")
    Float sumCostOperatingBetween(@Param("start") LocalDateTime start,
                                  @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.costIncurred), 0) " +
            "FROM BusTrip b " +
            "WHERE b.departureTime BETWEEN :start AND :end " +
            "AND EXISTS (SELECT 1 FROM Invoice i WHERE i.busTrip.id = b.id AND i.status = 3)")
    Float sumCostIncurredBetween(@Param("start") LocalDateTime start,
                                 @Param("end") LocalDateTime end);

    @Query(value = """
    SELECT DISTINCT bt.*
    FROM bustrip bt
    JOIN busroute br ON bt.id_bus_route = br.id
    JOIN route r1 ON r1.id_bus_route = br.id
    JOIN route r2 ON r2.id_bus_route = br.id
    JOIN busstations bs1 ON r1.id_bus_station = bs1.id
    JOIN busstations bs2 ON r2.id_bus_station = bs2.id
    WHERE bs1.id_province = :startProvinceId
      AND bs2.id_province = :endProvinceId
      AND r1.route_location < r2.route_location
      AND DATE(bt.departure_time) = :date
      AND bt.departure_time >= :currentTimePlusTwoHours
    """, nativeQuery = true)
    List<BusTrip> findTripsWithIntermediateStops(
            @Param("startProvinceId") int startStationId,
            @Param("endProvinceId") int endStationId,
            @Param("date") LocalDate date,
            @Param("currentTimePlusTwoHours") LocalDateTime currentTimePlusTwoHours
    );
}

