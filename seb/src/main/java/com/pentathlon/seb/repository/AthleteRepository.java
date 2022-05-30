package com.pentathlon.seb.repository;

import com.pentathlon.seb.domain.Athlete;

import java.util.List;

public interface AthleteRepository {
    List<Athlete> getAthletes();
}
