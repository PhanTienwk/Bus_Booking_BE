package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.InvoiceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {

    InvoiceRepository invoiceRepository;

    UserRepository userRepository;
    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }


    public List<Invoice> getInvoiceByUserId(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            log.warn("Không tìm thấy người dùng với số điện thoại: {}", phone);
            return List.of();
        }
        return invoiceRepository.findByUserId(user.getId());
    }
}
