package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
   List<Invoice> getInvoiceByUserId(String phone);
}
