package com.thuctap.busbooking.mapper;

import com.thuctap.busbooking.dto.request.DriverCreationRequest;
import com.thuctap.busbooking.dto.request.UserCreationRequest;
import com.thuctap.busbooking.dto.request.UserUpdateInfoRequest;
import com.thuctap.busbooking.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserCreation(UserCreationRequest request);
    User toDriverCreation(DriverCreationRequest request);
    User toUserUpdateInfo(UserUpdateInfoRequest request);
}
