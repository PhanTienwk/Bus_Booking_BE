package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.SeatPosition;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatPositionRepository extends JpaRepository<SeatPosition, Integer> {
    List<SeatPosition> findByBusId(Integer busId);

    SeatPosition findById(int seatPositionId);

    void deleteByBusId(int id);
    List<SeatPosition> findByBusIdAndStatusTrue(Integer busId);
    SeatPosition findByNameAndBusId(String name, int busId);
}
