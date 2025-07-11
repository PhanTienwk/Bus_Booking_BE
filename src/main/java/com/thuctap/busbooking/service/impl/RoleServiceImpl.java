package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.RoleRequest;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.mapper.RoleMapper;
import com.thuctap.busbooking.repository.RoleRepository;
import com.thuctap.busbooking.service.auth.RoleService;
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
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public Role createRole(RoleRequest request){
        if(roleRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.ROLE_EXISTED);
        Role role = roleMapper.toRole(request);
        return roleRepository.save(role);
    }

    public List<Role> getRoles(){
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new AppException(ErrorCode.LIST_ROLE_NULL);
        }
        return roles;
    }
}
