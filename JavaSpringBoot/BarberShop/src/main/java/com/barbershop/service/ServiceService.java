package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.barbershop.dto.ServiceDTO;
import com.barbershop.entity.Service;
import com.barbershop.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;

	public List<ServiceDTO> getAllServices() {
		return serviceRepository.findAll().stream().map(ServiceDTO::new).collect(Collectors.toList());
	}

	public ServiceDTO createService(ServiceDTO serviceDTO) {
		Service service = new Service();
		service.setName(serviceDTO.getName());
		service.setPrice(serviceDTO.getPrice());
		service.setDurationMinutes(serviceDTO.getDurationMinutes());
		// Set other fields
		return new ServiceDTO(serviceRepository.save(service));
	}

	public void deleteService(Long id) {
		serviceRepository.deleteById(id);
	}
}
