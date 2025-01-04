package com.barbershop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        // This will serve the Angular index.html
        return "forward:/index.html";
    }
}
