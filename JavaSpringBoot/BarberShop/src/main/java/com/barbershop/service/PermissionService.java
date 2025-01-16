package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.dto.PermissionDTO;
import com.barbershop.entity.Permission;
import com.barbershop.entity.RolePermission;
import com.barbershop.exception.Exception;
import com.barbershop.repository.PermissionRepository;
import com.barbershop.repository.RolePermissionRepository;
import com.barbershop.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    // Create a new permission
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
    	List<Permission> list = permissionRepository.findByName(permissionDTO.getName());
    	
    	if(list != null && list.size() > 0) {
    		throw new Exception("Permission: | " + permissionDTO.getName() + " | already exists.");
    	}
    	else {
    		Permission permission = new Permission();
            permission.setName(permissionDTO.getName());
            return new PermissionDTO(permissionRepository.save(permission));
    	}
    }

    // Get all permissions
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(permission -> new PermissionDTO(permission))
                .collect(Collectors.toList());
    }

    // Delete permission by ID
    public void deletePermission(Long permissionId) {
        if (rolePermissionRepository.existsByPermissionId(permissionId)) {
            throw new Exception("Permission is assigned to a role and cannot be deleted.");
        }
        permissionRepository.deleteById(permissionId);
    }

    // Get all permissions assigned to a role
    public List<PermissionDTO> getPermissionsByRoleId(Long roleId) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findPermissionsByRoleId(roleId);
        return rolePermissions.stream().map(rolePermission -> new PermissionDTO(rolePermission.getPermission()))
                .collect(Collectors.toList());
    }

    // Assign a permission to a role
    public void assignPermissionToRole(Long roleId, Long permissionId, boolean assign) {
    	if(assign) {
    		RolePermission rolePermission = new RolePermission();
            rolePermission.setRole(roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId)));
            rolePermission.setPermission(permissionRepository.findById(permissionId).orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + permissionId)));
            rolePermissionRepository.save(rolePermission);
    	}
    	else {
    		RolePermission rolePermission = rolePermissionRepository.findByRoleAndPermissionId(roleId, permissionId);
    		if(rolePermission != null && rolePermission.getId() > 0) {
    			rolePermissionRepository.delete(rolePermission);
    		}
    	}
    }

    // Remove permission from a role
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        rolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    // Update an existing permission
    public PermissionDTO updatePermission(PermissionDTO permissionDTO) {
        // Check if the permission exists
        Permission existingPermission = permissionRepository.findById(permissionDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + permissionDTO.getId()));
        
        List<Permission> list = permissionRepository.findByName(permissionDTO.getName());
    	
    	if(list != null && list.size() > 0) {
    		throw new Exception("Permission:" + permissionDTO.getName() + " already exists.");
    	}

        // Update the permission name (you can add more fields to update as needed)
        existingPermission.setName(permissionDTO.getName());

        // Save and return the updated permission
        return new PermissionDTO(permissionRepository.save(existingPermission));
    }
}
