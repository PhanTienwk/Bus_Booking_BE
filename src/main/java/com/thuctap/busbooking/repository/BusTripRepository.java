package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusTrip;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Integer>, JpaSpecificationExecutor<BusTrip> {}
