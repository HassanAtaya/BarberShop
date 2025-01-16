package com.barbershop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbershop.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
	Optional<Language> findByName(String name);
}