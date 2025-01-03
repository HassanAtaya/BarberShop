package com.barbershop.service;

import com.barbershop.dto.LanguageDTO;
import com.barbershop.entity.Language;
import com.barbershop.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

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
