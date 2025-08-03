package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.BankDetailRequest;
import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice createInvoice(InvoiceCreationRequest request);

   List<Invoice> getInvoiceByUserId(String phone);
    public Boolean updateInvoiceStatus(Integer id, Integer status);
    public Boolean addBankDetail(BankDetailRequest bankDetailRequest);

    List<Invoice> getInvoiceByUserId(String phone);
    void updateInvoiceStatus(Integer invoiceId, int status);

}
