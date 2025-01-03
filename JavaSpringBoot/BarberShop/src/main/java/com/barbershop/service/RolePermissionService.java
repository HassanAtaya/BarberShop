package com.barbershop.service;

import com.barbershop.dto.RolePermissionDTO;
import com.barbershop.entity.RolePermission;
import com.barbershop.repository.RolePermissionRepository;
import com.barbershop.util.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionService {
	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	public List<RolePermissionDTO> getAllRolePermissions() {
		return rolePermissionRepository.findAll().stream().map(RolePermissionDTO::new).collect(Collectors.toList());
	}

	public RolePermissionDTO createRolePermission(RolePermissionDTO rolePermissionDTO) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRole(Utilities.mapRoleDTOToRole(rolePermissionDTO.getRole()));
		rolePermission.setPermission(Utilities.mapPermissionDTOToPermission(rolePermissionDTO.getPermission()));
		// Set other fields
		return new RolePermissionDTO(rolePermissionRepository.save(rolePermission));
	}

	public void deleteRolePermission(Long id) {
		rolePermissionRepository.deleteById(id);
	}
}
