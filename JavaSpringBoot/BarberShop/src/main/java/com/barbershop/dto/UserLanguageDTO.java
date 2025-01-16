package com.barbershop.dto;

public class UserLanguageDTO {

	private Long userID;
	private Long languageID;

	public UserLanguageDTO() {

	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getLanguageID() {
		return languageID;
	}

	public void setLanguageID(Long languageID) {
		this.languageID = languageID;
	}

}
