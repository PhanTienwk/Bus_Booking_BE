package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStationRepository extends JpaRepository<BusStation,Integer> {
}
