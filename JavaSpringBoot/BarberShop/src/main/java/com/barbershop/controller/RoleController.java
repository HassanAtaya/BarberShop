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

import com.barbershop.dto.RoleDTO;
import com.barbershop.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping
	public List<RoleDTO> getAllRoles() {
		List<RoleDTO> list = roleService.getAllRoles();

		if (list != null && !list.isEmpty()) {
			list.removeIf(roleDTO -> "SuperAdmin".equals(roleDTO.getName()));
		}

		return list;
	}

	@PostMapping
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
		return roleService.createRole(roleDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
}