package com.epam.training.sportsbetting.web.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.WagerBuilder;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.OutcomeOddRepository;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;

@Component
public class WagerService {

	@Autowired
	private OutcomeOddRepository outcomeOddRepository;
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private OutcomeRepository outcomeRepository;
	@Autowired
	private WagerRepository wagerRepository;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private PlayerRepository playerRepository;

	public void removeWager(int id) {
		Player player = playerService.getLoggedInUser();
		Wager wager = wagerRepository.findById(id).get();
		wagerRepository.deleteById(id);
		BigDecimal newPlayerBalance = new BigDecimal(player.getBalance().intValue() + wager.getAmount().intValue());
		player.setBalance(newPlayerBalance);
		playerRepository.save(player);
	}

	public void showWagerOptions(int id, ModelAndView model) {
		Bet bet = betRepository.findById(id).get();
		List<Outcome> outcome = outcomeRepository.findOutcomeByBet(bet);
		model.addObject("bet", bet);
		model.addObject("outcome", outcome);
		model.setViewName("wagers");
	}

	public void saveNewWager(ModelAndView model, String amount, String description) {
		Player player = playerService.getLoggedInUser();
		Outcome outcome = outcomeRepository.findById(Integer.parseInt(description)).get();
		Wager wager = new WagerBuilder()
				.setAmount(new BigDecimal(Integer.parseInt(amount)))
				.setCurrency(player.getCurrency()).setOdd(outcomeOddRepository.findOutcomeOddByOutcome(outcome))
				.setPlayer(player)
				.setProcessed(true)
				.setTimestampCreated(null)
				.setWin(true)
				.build();
		if (isBalanceEnough(player, wager)) {
			wagerRepository.save(wager);
			BigDecimal newPlayerBalance = new BigDecimal(player.getBalance().intValue() - Integer.parseInt(amount));
			player.setBalance(newPlayerBalance);
			playerRepository.save(player);
		}
		model.setViewName("redirect:/home");
	}

	private boolean isBalanceEnough(Player player, Wager wager) {
		return player.getBalance().intValue() >= wager.getAmount().intValue();
	}
}
