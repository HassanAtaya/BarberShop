package com.barbershop.repository;

import com.barbershop.entity.AppointmentPriceService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentPriceServiceRepository extends JpaRepository<AppointmentPriceService, Long> {
}