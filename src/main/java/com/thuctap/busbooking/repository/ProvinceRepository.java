package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    boolean existsByName(String name);
}
