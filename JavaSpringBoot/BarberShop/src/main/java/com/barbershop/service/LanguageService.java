package com.barbershop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.dto.LanguageDTO;
import com.barbershop.dto.ResponseDTO;
import com.barbershop.dto.UserLanguageDTO;
import com.barbershop.entity.Language;
import com.barbershop.entity.User;
import com.barbershop.repository.LanguageRepository;
import com.barbershop.repository.UserRepository;

@RestController
@RequestMapping("/api/languages")
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/getAllLanguages")
	public List<LanguageDTO> getAllLanguages() {
		return languageRepository.findAll().stream().map(LanguageDTO::new).collect(Collectors.toList());
	}

	@PostMapping("/updateLanguageByUserID")
	public ResponseDTO updateLanguageByUserID(@RequestBody UserLanguageDTO userLanguageDTO) {
		if (userLanguageDTO.getLanguageID() != null && userLanguageDTO.getUserID() != null) {
			User user = userRepository.findById(userLanguageDTO.getUserID()).orElse(null);
			if (user != null) {
				Language language = languageRepository.findById(userLanguageDTO.getLanguageID()).orElse(null);
				if (language != null) {
					user.setLanguage(language);
					userRepository.save(user);
					return new ResponseDTO("Language Updated Successfully");
				} else {
					return new ResponseDTO("Language Not Found");
				}
			} else {
				return new ResponseDTO("User Not Found");
			}
		}
		return new ResponseDTO("Invalid Input");
	}

	public LanguageDTO createLanguage(LanguageDTO languageDTO) {
		Language language = new Language();
		language.setName(languageDTO.getName());
		return new LanguageDTO(languageRepository.save(language));
	}

	public void deleteLanguage(Long id) {
		languageRepository.deleteById(id);
	}

	@GetMapping("/getLanguageName")
	public Language getLanguageName(String name) {
		Language language = languageRepository.findByName(name).orElse(null);
		if (language != null) {
			return language;
		} else {
			return null;
		}
	}

}
