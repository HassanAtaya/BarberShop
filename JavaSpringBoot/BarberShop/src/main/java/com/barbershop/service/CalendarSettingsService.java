package com.barbershop.service;

import com.barbershop.dto.CalendarSettingsDTO;
import com.barbershop.entity.CalendarSettings;
import com.barbershop.repository.CalendarSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarSettingsService {
    @Autowired
    private CalendarSettingsRepository calendarSettingsRepository;

    public List<CalendarSettingsDTO> getAllCalendarSettings() {
        return calendarSettingsRepository.findAll().stream().map(CalendarSettingsDTO::new).collect(Collectors.toList());
    }

    public CalendarSettingsDTO createCalendarSettings(CalendarSettingsDTO calendarSettingsDTO) {
        CalendarSettings calendarSettings = new CalendarSettings();
        calendarSettings.setName(calendarSettingsDTO.getName());
        calendarSettings.setValue(calendarSettingsDTO.getValue());
        // Set other fields
        return new CalendarSettingsDTO(calendarSettingsRepository.save(calendarSettings));
    }

    public void deleteCalendarSettings(Long id) {
        calendarSettingsRepository.deleteById(id);
    }
}
