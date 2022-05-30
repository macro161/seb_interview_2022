package com.pentathlon.seb.repository;

import com.pentathlon.seb.domain.Athlete;
import com.pentathlon.seb.util.InMemoryDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AthleteInMemoryRepositoryImpl implements AthleteRepository {
    @Override
    public List<Athlete> getAthletes() {
        return InMemoryDatabase.athletes;
    }
}
