package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusStation;

import java.util.List;

@Repository
public interface BusStationRepository extends JpaRepository<BusStation, Integer> , JpaSpecificationExecutor<BusStation> {
    long countByStatus(int status);


    List<BusStation> findAllByOrderByIdDesc();
}
