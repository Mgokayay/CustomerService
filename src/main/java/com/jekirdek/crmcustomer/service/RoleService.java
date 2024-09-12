package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.RoleResponse;
import com.jekirdek.crmcustomer.entity.Role;

import java.util.List;

public interface RoleService {

    RoleResponse save(Role role);

    List<RoleResponse> findAll();

    RoleResponse findById(Long id);

    void delete(Long id);
}
