package com.barbershop.dto;

import com.barbershop.entity.CalendarSettings;

public class CalendarSettingsDTO {
	private Long id;
	private String name;
	private String value;

	public CalendarSettingsDTO() {
	}

	public CalendarSettingsDTO(CalendarSettings calendarSettings) {
		this.id = calendarSettings.getId();
		this.name = calendarSettings.getName();
		this.value = calendarSettings.getValue();
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}