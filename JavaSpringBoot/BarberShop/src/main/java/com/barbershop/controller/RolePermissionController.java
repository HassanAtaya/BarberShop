package com.barbershop.controller;

import com.barbershop.dto.RolePermissionDTO;
import com.barbershop.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role_permissions")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public List<RolePermissionDTO> getAllRolePermissions() {
        return rolePermissionService.getAllRolePermissions();
    }

    @PostMapping
    public RolePermissionDTO createRolePermission(@RequestBody RolePermissionDTO rolePermissionDTO) {
        return rolePermissionService.createRolePermission(rolePermissionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRolePermission(@PathVariable Long id) {
        rolePermissionService.deleteRolePermission(id);
    }
}