package com.barbershop.controller;

import com.barbershop.dto.CalendarSettingsDTO;
import com.barbershop.service.CalendarSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar_settings")
public class CalendarSettingsController {
    @Autowired
    private CalendarSettingsService calendarSettingsService;

    @GetMapping
    public List<CalendarSettingsDTO> getAllCalendarSettings() {
        return calendarSettingsService.getAllCalendarSettings();
    }

    @PostMapping
    public CalendarSettingsDTO createCalendarSettings(@RequestBody CalendarSettingsDTO calendarSettingsDTO) {
        return calendarSettingsService.createCalendarSettings(calendarSettingsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendarSettings(@PathVariable Long id) {
        calendarSettingsService.deleteCalendarSettings(id);
    }
}