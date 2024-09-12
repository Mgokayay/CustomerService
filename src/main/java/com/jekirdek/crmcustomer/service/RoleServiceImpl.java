package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.RoleResponse;
import com.jekirdek.crmcustomer.entity.Role;
import com.jekirdek.crmcustomer.exception.CommonException;
import com.jekirdek.crmcustomer.repository.RoleRepository;
import com.jekirdek.crmcustomer.util.RoleDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public RoleResponse save(Role role) {
        return RoleDtoConversion.convertRole(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> findAll() {
        return RoleDtoConversion.convertRoleList(roleRepository.findAll());
    }

    @Override
    public RoleResponse findById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            return RoleDtoConversion.convertRole(roleOptional.get());
        }
        throw new CommonException("Role not found with given id" + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public void delete(Long id) {
       Optional<Role> roleOptional = roleRepository.findById(id);
       if (roleOptional.isPresent()) {
           roleRepository.delete(roleOptional.get());
       }else{
           throw new CommonException("Role not found with given id" + id, HttpStatus.NOT_FOUND);
       }

    }
}
