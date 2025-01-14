package com.barbershop.dto;

public class ResponseDTO {

	private String text;
	
	public ResponseDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
