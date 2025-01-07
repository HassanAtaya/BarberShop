package com.barbershop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.UserDTO;
import com.barbershop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userName}")
	public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String userName) {
		UserDTO userDTO = userService.getUserByUserName(userName);
		return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		UserDTO loggedInUser = userService.login(userDTO);
		return loggedInUser != null ? ResponseEntity.ok(loggedInUser)
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
