package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.ChangeTicketRequest;
import com.thuctap.busbooking.entity.ChangeHistoryTicket;

public interface ChangeHistoryTicketService {
    ChangeHistoryTicket createChangeTicket(ChangeTicketRequest request);

}
