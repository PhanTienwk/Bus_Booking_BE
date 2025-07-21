package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();
    Account createAccountUser(AccountCreationRequest request);
    boolean verifyEmail(String email, String code);
    Account createAccountDriver(AccountCreationRequest request);
}
