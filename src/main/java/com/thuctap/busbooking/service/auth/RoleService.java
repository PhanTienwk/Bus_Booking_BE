package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.RoleRequest;
import com.thuctap.busbooking.entity.Role;

public interface RoleService {
    Role createRole(RoleRequest request);
}
