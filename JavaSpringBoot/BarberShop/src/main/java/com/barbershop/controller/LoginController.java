package com.barbershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.CredentialsDTO;
import com.barbershop.dto.ResponseDTO;
import com.barbershop.dto.UserDTO;
import com.barbershop.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO userDTO) {
		UserDTO loggedInUser = userService.login(userDTO);
		return loggedInUser;
	}

	@PostMapping("/logout")
	public ResponseDTO logout(@RequestBody CredentialsDTO credentialsDTO) {
		boolean isLoggedOut = userService.logout(credentialsDTO.getToken());

		if (isLoggedOut) {
			return new ResponseDTO("Logout successful.");
		} else {
			return new ResponseDTO("Invalid token or already logged out.");
		}
	}
}