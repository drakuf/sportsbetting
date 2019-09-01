package com.epam.training.sportsbetting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Result;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer>{
	List<Outcome> findOutcomeByBet(Bet bet);
	
	Outcome findByDescription(String description);
}
