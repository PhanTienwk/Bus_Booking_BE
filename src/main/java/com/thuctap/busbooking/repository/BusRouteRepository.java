package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.BusRoute;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer>, JpaSpecificationExecutor<BusRoute> {
    long countByStatus(int status);
}
