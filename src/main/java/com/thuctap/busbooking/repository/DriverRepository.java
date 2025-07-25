package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.dto.response.DriverScheduleResponse;
import com.thuctap.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u JOIN u.account a JOIN a.role r WHERE r.id = 2")
    List<User> findAllDrivers();

    @Query("""
    SELECT new com.thuctap.busbooking.dto.response.DriverScheduleResponse(
        trip.id,
        CONCAT(fromStation.name, ' - ', toStation.name),
        fromStation.address,
        toStation.address,
        bus.name,
        trip.departureTime,
        route.travelTime,
        bus.busType
    )
    FROM BusTrip trip
    JOIN trip.bus bus
    JOIN bus.busType busType
    JOIN trip.busRoute route
    JOIN route.busStationFrom fromStation
    JOIN route.busStationTo toStation
    WHERE trip.user.id = :driverId
    AND trip.departureTime BETWEEN :startDate AND :endDate
    ORDER BY trip.departureTime
    """)
    List<DriverScheduleResponse> getScheduleByDriverAndDateRange(
            @Param("driverId") Integer driverId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
