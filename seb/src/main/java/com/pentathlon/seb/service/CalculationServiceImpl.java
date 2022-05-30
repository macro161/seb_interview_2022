package com.pentathlon.seb.service;

import com.pentathlon.seb.domain.Athlete;
import com.pentathlon.seb.repository.AthleteInMemoryRepositoryImpl;
import com.pentathlon.seb.repository.AthleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private AthleteRepository repository;

    @Override
    public List<Athlete> calculate() {
        List<Athlete> athletes = repository.getAthletes();
        calculateShootingScore(athletes);
        calculateFencingScore(athletes);
        return null;
    }

    private void calculateFencingScore(List<Athlete> athletes) {
        int count = athletes.size() - 1;

        double marker = Math.ceil(count * 0.7);

        Athlete athlete = athletes.get(0);

        if (athlete.getFencingVictories() == marker) {
            athlete.setTotalScore(athlete.getTotalScore() + 1000);
        }

        if (athlete.getFencingVictories() > marker) {
            int score = 1000 + (athlete)
        }

    }

    private void calculateShootingScore(List<Athlete> athletes) {
        for(Athlete athlete : athletes) {
            int score = 1000 + ((athlete.getShootingTargetScore() - 172 ) * 12);
            athlete.setTotalScore(athlete.getTotalScore() + score);
        }
    }
}
