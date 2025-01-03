package com.barbershop.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rate1Name;
	private String rate2Name;
	private int rate1Nbr;
	private int rate2Nbr;
	private LocalDateTime date;
	private boolean use;
	private Long lastUpdator;
	private LocalDateTime lastUpdate;

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