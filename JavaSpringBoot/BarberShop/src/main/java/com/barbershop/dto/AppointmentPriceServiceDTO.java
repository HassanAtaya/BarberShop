package com.barbershop.dto;

import com.barbershop.entity.AppointmentPriceService;

import java.math.BigDecimal;

public class AppointmentPriceServiceDTO {
	private Long id;
	private RateDTO rate;
	private ServiceDTO service;
	private BigDecimal price;
	private BigDecimal price2;

	public AppointmentPriceServiceDTO() {
	}

	public AppointmentPriceServiceDTO(AppointmentPriceService appointmentPriceService) {
		this.id = appointmentPriceService.getId();
		this.rate = new RateDTO(appointmentPriceService.getRate());
		this.service = new ServiceDTO(appointmentPriceService.getService());
		this.price = appointmentPriceService.getPrice();
		this.price2 = appointmentPriceService.getPrice2();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RateDTO getRate() {
		return rate;
	}

	public void setRate(RateDTO rate) {
		this.rate = rate;
	}

	public ServiceDTO getService() {
		return service;
	}

	public void setService(ServiceDTO service) {
		this.service = service;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

}