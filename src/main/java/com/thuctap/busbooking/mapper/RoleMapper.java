package com.thuctap.busbooking.mapper;

import com.thuctap.busbooking.dto.request.RoleRequest;
import com.thuctap.busbooking.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);
}
