package com.barbershop.util;

import com.barbershop.dto.*;
import com.barbershop.entity.*;

public class Utilities {

	public static Role mapRoleDTOToRole(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		return role;
	}

	public static RoleDTO mapRoleToRoleDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		return roleDTO;
	}

	public static Permission mapPermissionDTOToPermission(PermissionDTO permissionDTO) {
		Permission permission = new Permission();
		permission.setId(permissionDTO.getId());
		permission.setName(permissionDTO.getName());
		return permission;
	}

	public static PermissionDTO mapPermissionToPermissionDTO(Permission permission) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permission.getId());
		permissionDTO.setName(permission.getName());
		return permissionDTO;
	}

	public static User mapUserDTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		Role role = new Role();
		role.setName(userDTO.getRoleName());
		user.setRole(role);
		Language language = new Language();
		language.setName(userDTO.getLanguageName());
		user.setLanguage(language);
		return user;
	}

	public static UserDTO mapUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(user.getPassword());
		userDTO.setRoleName(user.getRole().getName());
		userDTO.setLanguageName(user.getLanguage().getName());
		return userDTO;
	}

}
