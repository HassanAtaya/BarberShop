package com.barbershop.repository;

import com.barbershop.entity.CalendarSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarSettingsRepository extends JpaRepository<CalendarSettings, Long> {
}