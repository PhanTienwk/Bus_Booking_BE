package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip,Integer> {
}
