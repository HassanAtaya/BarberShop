package com.barbershop.service;

import com.barbershop.dto.RateDTO;
import com.barbershop.entity.Rate;
import com.barbershop.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    public List<RateDTO> getAllRates() {
        return rateRepository.findAll().stream().map(RateDTO::new).collect(Collectors.toList());
    }

    public RateDTO createRate(RateDTO rateDTO) {
        Rate rate = new Rate();
        rate.setRate1Name(rateDTO.getRate1Name());
        rate.setRate2Name(rateDTO.getRate2Name());
        rate.setRate1Nbr(rateDTO.getRate1Nbr());
        rate.setRate2Nbr(rateDTO.getRate2Nbr());
        rate.setDate(rateDTO.getDate());
        rate.setUse(rateDTO.isUse());
        // Set other fields
        return new RateDTO(rateRepository.save(rate));
    }

    public void deleteRate(Long id) {
        rateRepository.deleteById(id);
    }
}
