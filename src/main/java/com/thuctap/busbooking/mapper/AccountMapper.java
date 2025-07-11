package com.thuctap.busbooking.mapper;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreationRequest request);
}
