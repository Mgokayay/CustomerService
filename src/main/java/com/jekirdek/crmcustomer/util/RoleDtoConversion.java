package com.jekirdek.crmcustomer.util;

import com.jekirdek.crmcustomer.dto.RoleResponse;
import com.jekirdek.crmcustomer.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDtoConversion {

    public static List<RoleResponse> convertRoleList(List<Role> roleList) {
        List<RoleResponse> roleResponseList =roleList.stream().map(role -> new RoleResponse(
                role.getId(),
                role.getRoleName()
        )).collect(Collectors.toList());

        return roleResponseList;
    }

    public static RoleResponse convertRole(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getRoleName());
    }
}
