package com.barbershop.controller;

import com.barbershop.dto.AppointmentPriceServiceDTO;
import com.barbershop.service.AppointmentPriceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment_price_services")
public class AppointmentPriceServiceController {
    @Autowired
    private AppointmentPriceServiceService appointmentPriceServiceService;

    @GetMapping
    public List<AppointmentPriceServiceDTO> getAllAppointmentPriceServices() {
        return appointmentPriceServiceService.getAllAppointmentPriceServices();
    }

    @PostMapping
    public AppointmentPriceServiceDTO createAppointmentPriceService(@RequestBody AppointmentPriceServiceDTO appointmentPriceServiceDTO) {
        return appointmentPriceServiceService.createAppointmentPriceService(appointmentPriceServiceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointmentPriceService(@PathVariable Long id) {
        appointmentPriceServiceService.deleteAppointmentPriceService(id);
    }
}