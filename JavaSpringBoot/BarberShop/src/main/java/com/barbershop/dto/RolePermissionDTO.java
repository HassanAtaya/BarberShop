package com.barbershop.dto;

import com.barbershop.entity.RolePermission;

public class RolePermissionDTO {
	private Long id;
	private RoleDTO role;
	private PermissionDTO permission;

	public RolePermissionDTO() {
	}

	public RolePermissionDTO(RolePermission rolePermission) {
		this.id = rolePermission.getId();
		this.role = new RoleDTO(rolePermission.getRole());
		this.permission = new PermissionDTO(rolePermission.getPermission());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public PermissionDTO getPermission() {
		return permission;
	}

	public void setPermission(PermissionDTO permission) {
		this.permission = permission;
	}

}