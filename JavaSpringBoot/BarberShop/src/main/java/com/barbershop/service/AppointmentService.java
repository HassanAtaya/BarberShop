package com.barbershop.service;

import com.barbershop.dto.AppointmentDTO;
import com.barbershop.entity.Appointment;
import com.barbershop.repository.AppointmentRepository;
import com.barbershop.util.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	public List<AppointmentDTO> getAllAppointments() {
		return appointmentRepository.findAll().stream().map(AppointmentDTO::new).collect(Collectors.toList());
	}

	public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		
		appointment.setDate(appointmentDTO.getDate());
		appointment.setTimeFrom(appointmentDTO.getTimeFrom());
		appointment.setTimeTo(appointmentDTO.getTimeTo());
		appointment.setUser(Utilities.mapUserDTOToUser(appointmentDTO.getUser()));
		appointment.setManager(Utilities.mapUserDTOToUser(appointmentDTO.getManager()));
		appointment.setAppointmentPriceService(Utilities.mapAppointmentPriceServiceDTOToAppointmentPriceService(appointmentDTO.getAppointmentPriceService()));
		appointment.setPaid(appointmentDTO.isPaid());
		appointment.setAction(appointmentDTO.getAction());

		return new AppointmentDTO(appointmentRepository.save(appointment));
	}

	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}
}
