package com.jekirdek.crmcustomer.controller;

import com.jekirdek.crmcustomer.dto.RoleResponse;
import com.jekirdek.crmcustomer.entity.Role;
import com.jekirdek.crmcustomer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleResponse addRole(@RequestBody Role role){
        return roleService.save(role);
    }

    @GetMapping
    public List<RoleResponse> getAllRoles(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleResponse getRoleById(@PathVariable Long id){
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable Long id){
        roleService.delete(id);
    }
}
