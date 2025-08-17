package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.BankDetails;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.repository.BankDTReponsitory;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.service.auth.BankDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BankDetailServiceImlp implements BankDetailService {

   BankDTReponsitory bankDTReponsitory;


    public List<BankDetails> getAllBankDT() {
        return bankDTReponsitory.findAll();
    }
}
