package com.barbershop.dto;

import com.barbershop.entity.Appointment;

import java.time.LocalDateTime;

public class AppointmentDTO {
	private Long id;
	private LocalDateTime date;
	private LocalDateTime timeFrom;
	private LocalDateTime timeTo;
	private UserDTO user;
	private UserDTO manager;
	private AppointmentPriceServiceDTO appointmentPriceService;
	private boolean paid;
	private String action;

	public AppointmentDTO() {
	}

	public AppointmentDTO(Appointment appointment) {
		this.id = appointment.getId();
		this.date = appointment.getDate();
		this.timeFrom = appointment.getTimeFrom();
		this.timeTo = appointment.getTimeTo();
		this.user = new UserDTO(appointment.getUser());
		this.manager = new UserDTO(appointment.getManager());
		this.appointmentPriceService = new AppointmentPriceServiceDTO(appointment.getAppointmentPriceService());
		this.paid = appointment.isPaid();
		this.action = appointment.getAction();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(LocalDateTime timeFrom) {
		this.timeFrom = timeFrom;
	}

	public LocalDateTime getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(LocalDateTime timeTo) {
		this.timeTo = timeTo;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public UserDTO getManager() {
		return manager;
	}

	public void setManager(UserDTO manager) {
		this.manager = manager;
	}

	public AppointmentPriceServiceDTO getAppointmentPriceService() {
		return appointmentPriceService;
	}

	public void setAppointmentPriceService(AppointmentPriceServiceDTO appointmentPriceService) {
		this.appointmentPriceService = appointmentPriceService;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}