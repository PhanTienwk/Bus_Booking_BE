package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.BankDetailRequest;
import com.thuctap.busbooking.dto.request.InvoiceCreationRequest;
import com.thuctap.busbooking.dto.request.InvoiceFilterRequest;
import com.thuctap.busbooking.dto.request.InvoiceUpdateRequest;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice createInvoice(InvoiceCreationRequest request);
    Invoice getInvoiceId(int id);
    Invoice updateInvoice(int id, InvoiceUpdateRequest request);

   List<Invoice> getInvoiceByUserId(int id);
    public Boolean updateInvoiceStatus(Integer id, Integer status);
    public Boolean addBankDetail(BankDetailRequest bankDetailRequest);

    void updateInvoiceStatus(Integer invoiceId, int status);
    List<Invoice> filterInvoices(InvoiceFilterRequest filter);

}
