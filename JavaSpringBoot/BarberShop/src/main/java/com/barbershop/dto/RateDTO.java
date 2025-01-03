package com.barbershop.dto;

import com.barbershop.entity.Rate;

import java.time.LocalDateTime;

public class RateDTO {
	private Long id;
	private String rate1Name;
	private String rate2Name;
	private int rate1Nbr;
	private int rate2Nbr;
	private LocalDateTime date;
	private boolean use;

	public RateDTO() {
	}

	public RateDTO(Rate rate) {
		this.id = rate.getId();
		this.rate1Name = rate.getRate1Name();
		this.rate2Name = rate.getRate2Name();
		this.rate1Nbr = rate.getRate1Nbr();
		this.rate2Nbr = rate.getRate2Nbr();
		this.date = rate.getDate();
		this.use = rate.isUse();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRate1Name() {
		return rate1Name;
	}

	public void setRate1Name(String rate1Name) {
		this.rate1Name = rate1Name;
	}

	public String getRate2Name() {
		return rate2Name;
	}

	public void setRate2Name(String rate2Name) {
		this.rate2Name = rate2Name;
	}

	public int getRate1Nbr() {
		return rate1Nbr;
	}

	public void setRate1Nbr(int rate1Nbr) {
		this.rate1Nbr = rate1Nbr;
	}

	public int getRate2Nbr() {
		return rate2Nbr;
	}

	public void setRate2Nbr(int rate2Nbr) {
		this.rate2Nbr = rate2Nbr;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

}