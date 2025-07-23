package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>, JpaSpecificationExecutor<Bus> {
    long countByStatus(int status);

    boolean existsByName(String nameAdd);
}
