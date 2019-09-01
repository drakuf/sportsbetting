package com.epam.training.sportsbetting.ui;

import java.math.BigDecimal;
import java.util.List;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public interface IO {
	Player readPlayerData();

	void printWelcomeMessage(Player player);

	void printBalance(Player player);

	void printOutcomeOdds(List<SportEvent> list);

	OutcomeOdd selectOutcomeOdd(List<SportEvent> list);

	BigDecimal readWagerAmount();

	void printWagerSaved(Wager wager);

	void printNotEnoughBalance(Player player);

	void printResults(Player player, List<Wager> list);
}
