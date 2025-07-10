package com.thuctap.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
