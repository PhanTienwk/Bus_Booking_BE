package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDTReponsitory extends JpaRepository<BankDetails,Integer> {
}
