package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.RoleDTO;
import com.barbershop.entity.Role;
import com.barbershop.entity.RolePermission;
import com.barbershop.entity.User;
import com.barbershop.exception.Exception;
import com.barbershop.repository.RolePermissionRepository;
import com.barbershop.repository.RoleRepository;
import com.barbershop.repository.UserRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@GetMapping("/getAllRoles")
	public List<RoleDTO> getAllRoles() {
		return roleRepository.findAll().stream().map(RoleDTO::new).collect(Collectors.toList());
	}

	public RoleDTO createRole(RoleDTO roleDTO) {
		Role role = new Role();
		role.setName(roleDTO.getName());
		
		Role r = roleRepository.findByName(roleDTO.getName()).orElse(null);
		if(r != null && r.getName().equalsIgnoreCase(roleDTO.getName())) {
			throw new Exception("Role " + r.getName() + " already exists");
		}
		else {
			return new RoleDTO(roleRepository.save(role));	
		}
	}

	public void deleteRole(Long id) {
		List<User> listUsers = userRepository.findUsersByRoleId(id);
		List<RolePermission> listRolePermission = rolePermissionRepository.findPermissionsByRoleId(id);
		
		if(listUsers != null && listUsers.size() > 0) {
			throw new Exception("Role exits in user:" + listUsers.get(0).getUserName());
		}
		else if(listRolePermission != null && listRolePermission.size() > 0) {
			throw new Exception("Role exits in permission:" + listRolePermission.get(0).getPermission().getName());
		}
		else {
			roleRepository.deleteById(id);
		}
	}

	@GetMapping("/getRoleByName")
	public Role getRoleByName(String name) {
		Role role = roleRepository.findByName(name).orElse(null);
		if (role != null) {
			return role;
		} else {
			return null;
		}
	}

	@GetMapping("/getRoleByID")
	public Role getRoleByID(Long id) {
		Role role = roleRepository.findById(id).orElse(null);
		if (role != null) {
			return role;
		} else {
			return null;
		}
	}
}
