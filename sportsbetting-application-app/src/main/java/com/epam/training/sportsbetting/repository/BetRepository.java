package com.epam.training.sportsbetting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.SportEvent;

public interface BetRepository extends JpaRepository<Bet, Integer> {
	List<Bet> findBetsByEvent(SportEvent sportEvent);
}
