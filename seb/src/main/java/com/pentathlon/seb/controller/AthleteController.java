package com.pentathlon.seb.controller;

import com.pentathlon.seb.domain.Athlete;
import com.pentathlon.seb.service.CalculationServiceImpl;
import com.pentathlon.seb.util.InMemoryDatabase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AthleteController {

    private CalculationServiceImpl service;

    @GetMapping("/athletes")
    List<Athlete> getAllAthletes() {
        return service.calculate();
    }
}
