package com.barbershop.controller;

import com.barbershop.dto.LanguageDTO;
import com.barbershop.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @PostMapping
    public LanguageDTO createLanguage(@RequestBody LanguageDTO languageDTO) {
        return languageService.createLanguage(languageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
    }
}