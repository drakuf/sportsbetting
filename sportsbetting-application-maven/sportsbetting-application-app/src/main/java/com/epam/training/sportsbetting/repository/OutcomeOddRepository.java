package com.epam.training.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

public interface OutcomeOddRepository extends JpaRepository<OutcomeOdd, Integer>{
	OutcomeOdd findOutcomeOddByOutcome(Outcome outcome);
}
