package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.dto.AppointmentPriceServiceDTO;
import com.barbershop.entity.AppointmentPriceService;
import com.barbershop.repository.AppointmentPriceServiceRepository;
import com.barbershop.util.Utilities;

@Service
public class AppointmentPriceServiceService {
	@Autowired
	private AppointmentPriceServiceRepository appointmentPriceServiceRepository;

	public List<AppointmentPriceServiceDTO> getAllAppointmentPriceServices() {
		return appointmentPriceServiceRepository.findAll().stream().map(AppointmentPriceServiceDTO::new).collect(Collectors.toList());
	}

	public AppointmentPriceServiceDTO createAppointmentPriceService(AppointmentPriceServiceDTO appointmentPriceServiceDTO) {
		AppointmentPriceService appointmentPriceService = new AppointmentPriceService();
		
		appointmentPriceService.setRate(Utilities.mapRateDTOToRate(appointmentPriceServiceDTO.getRate()));
		appointmentPriceService.setService(Utilities.mapServiceDTOToService(appointmentPriceServiceDTO.getService()));
		appointmentPriceService.setPrice(appointmentPriceServiceDTO.getPrice());
		appointmentPriceService.setPrice2(appointmentPriceServiceDTO.getPrice2());
		
		return new AppointmentPriceServiceDTO(appointmentPriceServiceRepository.save(appointmentPriceService));
	}

	public void deleteAppointmentPriceService(Long id) {
		appointmentPriceServiceRepository.deleteById(id);
	}
}
