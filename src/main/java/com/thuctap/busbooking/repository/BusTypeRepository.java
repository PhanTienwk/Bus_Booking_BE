package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusType;

@Repository
public interface BusTypeRepository extends JpaRepository<BusType, Integer> {}
