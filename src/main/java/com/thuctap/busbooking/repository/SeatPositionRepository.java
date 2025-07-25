package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.SeatPosition;

import java.util.List;

@Repository
public interface SeatPositionRepository extends JpaRepository<SeatPosition, Integer> {
    List<SeatPosition> findByBusId(Integer busId);
    void deleteByBusId(int id);
}
