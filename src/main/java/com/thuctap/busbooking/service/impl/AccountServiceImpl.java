package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.service.auth.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
}
