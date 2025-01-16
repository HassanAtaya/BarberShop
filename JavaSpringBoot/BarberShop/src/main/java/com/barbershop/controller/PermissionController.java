package com.barbershop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.PermissionDTO;
import com.barbershop.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Endpoint for getting all permissions
    @PostMapping("/getPermissions")
    public List<PermissionDTO> getPermissions() {
        List<PermissionDTO> list = permissionService.getAllPermissions();
        if (list != null && !list.isEmpty()) {
            list.removeIf(permissionDTO -> "ALL_PERMISSIONS".equals(permissionDTO.getName()));
        }
        return list;
    }

    // Endpoint for adding a new permission
    @PostMapping("/addPermission")
    public PermissionDTO addPermission(@RequestBody PermissionDTO permissionDTO) {
        return permissionService.createPermission(permissionDTO);
    }

    // Endpoint for updating an existing permission (Changed to POST)
    @PostMapping("/updatePermission")
    public PermissionDTO updatePermission(@RequestBody PermissionDTO permissionDTO) {
		return permissionService.updatePermission(permissionDTO);
    }

    // Endpoint for deleting a permission (Changed to POST)
    @PostMapping("/deletePermission")
    public void deletePermission(@RequestBody PermissionDTO permissionDTO) {
        permissionService.deletePermission(permissionDTO.getId());
    }

    // Endpoint for getting permissions assigned to a role
    @PostMapping("/getPermissionsByRole")
    public List<PermissionDTO> getPermissionsByRole(@RequestBody PermissionDTO permissionDTO) {
        return permissionService.getPermissionsByRoleId(permissionDTO.getRoleId());
    }

    // Endpoint for toggling permission assignment for a role
    @PostMapping("/togglePermissionForRole")
    public void togglePermissionForRole(@RequestBody PermissionDTO permissionDTO) {
        permissionService.assignPermissionToRole(permissionDTO.getRoleId(), permissionDTO.getId(), permissionDTO.isAssign());
    }
}
