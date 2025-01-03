package com.barbershop.util;

import com.barbershop.dto.*;
import com.barbershop.entity.*;

public class Utilities {

	public static Rate mapRateDTOToRate(RateDTO rateDTO) {
		Rate rate = new Rate();
		rate.setId(rateDTO.getId());
		rate.setRate1Name(rateDTO.getRate1Name());
		rate.setRate2Name(rateDTO.getRate2Name());
		rate.setRate1Nbr(rateDTO.getRate1Nbr());
		rate.setRate2Nbr(rateDTO.getRate2Nbr());
		rate.setDate(rateDTO.getDate());
		rate.setUse(rateDTO.isUse());
		return rate;
	}

	public static RateDTO mapRateToRateDTO(Rate rate) {
		RateDTO rateDTO = new RateDTO();
		rateDTO.setId(rate.getId());
		rateDTO.setRate1Name(rate.getRate1Name());
		rateDTO.setRate2Name(rate.getRate2Name());
		rateDTO.setRate1Nbr(rate.getRate1Nbr());
		rateDTO.setRate2Nbr(rate.getRate2Nbr());
		rateDTO.setDate(rate.getDate());
		rateDTO.setUse(rate.isUse());
		return rateDTO;
	}

	public static Service mapServiceDTOToService(ServiceDTO serviceDTO) {
		Service service = new Service();
		service.setId(serviceDTO.getId());
		service.setName(serviceDTO.getName());
		service.setPrice(serviceDTO.getPrice());
		service.setDurationMinutes(serviceDTO.getDurationMinutes());
		return service;
	}

	public static ServiceDTO mapServiceToServiceDTO(Service service) {
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setId(service.getId());
		serviceDTO.setName(service.getName());
		serviceDTO.setPrice(service.getPrice());
		serviceDTO.setDurationMinutes(service.getDurationMinutes());
		return serviceDTO;
	}

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

	public static AppointmentPriceService mapAppointmentPriceServiceDTOToAppointmentPriceService(AppointmentPriceServiceDTO dto) {
		AppointmentPriceService entity = new AppointmentPriceService();
		entity.setId(dto.getId());
		entity.setRate(mapRateDTOToRate(dto.getRate()));
		entity.setService(mapServiceDTOToService(dto.getService()));
		entity.setPrice(dto.getPrice());
		entity.setPrice2(dto.getPrice2());
		return entity;
	}

	public static AppointmentPriceServiceDTO mapAppointmentPriceServiceToAppointmentPriceServiceDTO(AppointmentPriceService entity) {
		AppointmentPriceServiceDTO dto = new AppointmentPriceServiceDTO();
		dto.setId(entity.getId());
		dto.setRate(mapRateToRateDTO(entity.getRate()));
		dto.setService(mapServiceToServiceDTO(entity.getService()));
		dto.setPrice(entity.getPrice());
		dto.setPrice2(entity.getPrice2());
		return dto;
	}

}
