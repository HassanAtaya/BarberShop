package com.barbershop.dto;

import com.barbershop.entity.User;

public class UserDTO {
	private Long id;
	private String userName;
	private String password;
	private String roleName;
	private String languageName;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.roleName = (user.getRole() != null) ? user.getRole().getName() : null;
        this.languageName = (user.getLanguage() != null) ? user.getLanguage().getName() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}