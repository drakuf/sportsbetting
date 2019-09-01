package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;

public class DefaultSportEvents {

	public List<SportEvent> generateSportEvents() {
		List<SportEvent> sportEvents = new ArrayList<>();
		List<Bet> bets = new ArrayList<>();
		List<Outcome> outcomes = new ArrayList<>();
		List<OutcomeOdd> outcomeOdds = new ArrayList<>();
		Bet[] betsArray = new Bet[4];
		BetType[] betsType = { BetType.PLAYERS_SCORE, BetType.GOALS, BetType.WINNER };
		String[] betsDescription = { "player Oliver Giroud score", "number of scored goals", "winner" };

		Outcome[] betsOutcomes = new Outcome[4];
		String[] betsOutcomesDescription = { "1", "3", "Arsenal", "Chelsea" };

		OutcomeOdd[] betsOutcomeOdds = new OutcomeOdd[4];
		BigDecimal[] outcomeOddsValues = { new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("2"),
				new BigDecimal("3") };
		LocalDateTime validFrom = LocalDateTime.of(2000, Month.JANUARY, 1, 12, 0);
		LocalDateTime validUntil = LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0);
		Result result = new Result();

		SportEvent sportEvent = makeSportEvent(bets, result);
		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				betsArray[i] = new Bet();
				betsArray[i].setType(betsType[i]);
				betsArray[i].setDescription(betsDescription[i]);
				betsArray[i].setOutcomes(outcomes);
				betsArray[i].setEvent(sportEvent);

				bets.add(betsArray[i]);
			}
			betsOutcomes[i] = new Outcome();
			betsOutcomes[i].setDescription(betsOutcomesDescription[i]);
			if (i == 3) {
				betsOutcomes[i].setBet(betsArray[2]);
			} else {
				betsOutcomes[i].setBet(betsArray[i]);
			}
			betsOutcomes[i].setOutcomeOdds(outcomeOdds);

			outcomes.add(betsOutcomes[i]);

			betsOutcomeOdds[i] = new OutcomeOdd();
			betsOutcomeOdds[i].setValue(outcomeOddsValues[i]);
			betsOutcomeOdds[i].setValidFrom(validFrom);
			betsOutcomeOdds[i].setValidUntil(validUntil);
			betsOutcomeOdds[i].setOutcome(betsOutcomes[i]);

			outcomeOdds.add(betsOutcomeOdds[i]);
		}
		sportEvents.add(sportEvent);
		return sportEvents;
	}

	private SportEvent makeSportEvent(List<Bet> bets, Result result) {
		SportEvent sportEvent = new FootballSportEvent();
		sportEvent.setTitle("Arsenal vs Chelsea");
		sportEvent.setStartDate(LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0));
		sportEvent.setEndDate(LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0));
		sportEvent.setBets(bets);
		sportEvent.setResult(result);
		return sportEvent;
	}
}
