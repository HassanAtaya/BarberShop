package com.barbershop.dto;

import com.barbershop.entity.Language;

public class LanguageDTO {
	private Long id;
	private String name;

	public LanguageDTO() {
	}

	public LanguageDTO(Language language) {
		this.id = language.getId();
		this.name = language.getName();
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

}