package com.barbershop.dto;

import com.barbershop.entity.Service;

import java.math.BigDecimal;

public class ServiceDTO {
	private Long id;
	private String name;
	private BigDecimal price;
	private int durationMinutes;

	public ServiceDTO() {
	}

	public ServiceDTO(Service service) {
		this.id = service.getId();
		this.name = service.getName();
		this.price = service.getPrice();
		this.durationMinutes = service.getDurationMinutes();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

}