package com.barbershop.controller;

import com.barbershop.dto.RateDTO;
import com.barbershop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    @Autowired
    private RateService rateService;

    @GetMapping
    public List<RateDTO> getAllRates() {
        return rateService.getAllRates();
    }

    @PostMapping
    public RateDTO createRate(@RequestBody RateDTO rateDTO) {
        return rateService.createRate(rateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
    }
}