package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u JOIN u.account a JOIN a.role r WHERE r.id = 2")
    List<User> findAllDrivers();
}
