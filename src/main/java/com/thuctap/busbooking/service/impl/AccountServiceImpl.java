package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.mapper.AccountMapper;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.RoleRepository;
import com.thuctap.busbooking.service.auth.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account createAccountUser(AccountCreationRequest request){
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT_EXIST);
        Account account =accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setStatus(1);
        Role role = roleRepository.findByName("USER");
        account.setRole(role);
        return accountRepository.save(account);
    }
}
