package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.BusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BysTypeRepository extends JpaRepository<BusType,Integer> {
}
