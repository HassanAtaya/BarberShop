package com.barbershop.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;
	private LocalDateTime timeFrom;
	private LocalDateTime timeTo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private User manager;

	@ManyToOne
	@JoinColumn(name = "appointment_price_service_id")
	private AppointmentPriceService appointmentPriceService;

	private boolean paid;
	private String action;
	private Long lastUpdator;
	private LocalDateTime lastUpdate;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public AppointmentPriceService getAppointmentPriceService() {
		return appointmentPriceService;
	}

	public void setAppointmentPriceService(AppointmentPriceService appointmentPriceService) {
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

	public Long getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}