package com.barbershop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.RolePermissionDTO;
import com.barbershop.service.RolePermissionService;

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