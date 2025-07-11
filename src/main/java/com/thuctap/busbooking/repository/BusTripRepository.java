package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusTrip;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Integer> {}
