package com.barbershop.controller;

import com.barbershop.entity.User;
import com.barbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public String login(@RequestBody User user) {
    	try {
    		User existingUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if (existingUser != null) {
                return "Hi " + existingUser.getUserName();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "Invalid username or password";
    }
}
