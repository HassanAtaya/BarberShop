package com.barbershop.dto;

import com.barbershop.entity.Permission;

public class PermissionDTO {
	private Long id;
	private String name;

	public PermissionDTO() {
	}

	public PermissionDTO(Permission permission) {
		this.id = permission.getId();
		this.name = permission.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}