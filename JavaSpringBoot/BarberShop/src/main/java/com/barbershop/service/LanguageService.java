package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.LanguageDTO;
import com.barbershop.entity.Language;
import com.barbershop.repository.LanguageRepository;

@RestController
@RequestMapping("/api/languages")
public class LanguageService {
	
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("/getAllLanguages")
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll().stream().map(LanguageDTO::new).collect(Collectors.toList());
    }

    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        Language language = new Language();
        language.setName(languageDTO.getName());
        // Set other fields
        return new LanguageDTO(languageRepository.save(language));
    }

    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }
}
