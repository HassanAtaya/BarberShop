package com.barbershop.controller;

import com.barbershop.dto.UserDTO;
import com.barbershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
}