package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.SeatPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatPositionRepository extends JpaRepository<SeatPosition,Integer> {
}
