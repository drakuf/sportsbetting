package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.WagerBuilder;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;
import com.epam.training.sportsbetting.ui.IO;

@Component
@ComponentScan("com.epam.training.sportsbetting")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource(value = { "classpath:config.properties" }, encoding = "UTF-8")
public class AppService {

	@Autowired
	private SportsBettingService service;
	@Autowired
	private IO io;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private WagerRepository wagerRepository;
	@Autowired
	private SportEventRepository sportEventRepository;

	public void play() {
		uploadSportEvents();
		createPlayer();
		doBetting();
		calculateResults();
		printResults();
	}

	public void uploadSportEvents() {
		DefaultSportEvents sportEvents = new DefaultSportEvents();
		if (sportEventRepository.findAll() == null || sportEventRepository.findAll().isEmpty()) {
			sportEventRepository.saveAll(sportEvents.generateSportEvents());
		}
	}

	private void createPlayer() {
		Player player = io.readPlayerData();
		service.savePlayer(player);
		io.printWelcomeMessage(player);
		io.printBalance(player);
	}

	private void doBetting() {
		List<SportEvent> sportEvents = service.findAllSportEvents();
		boolean condition = true;
		while (condition && service.findPlayer().getBalance().intValue() > 0) {
			OutcomeOdd odd = io.selectOutcomeOdd(sportEvents);
			if (odd != null) {
				Wager wager = createWager(odd);
				BigDecimal playerBalance = service.findPlayer().getBalance();
				refreshBetAmount(wager, playerBalance);
				service.saveWager(wager);
				io.printWagerSaved(wager);
				Player player = service.findPlayer();
				io.printBalance(player);
				playerRepository.save(player);
			} else {
				condition = false;
			}
		}
	}

	private void refreshBetAmount(Wager wager, BigDecimal playerBalance) {
		while (wager.getAmount().compareTo(playerBalance) > 0) {
			io.printNotEnoughBalance(service.findPlayer());
			wager.setAmount(io.readWagerAmount());
		}
	}

	private Wager createWager(OutcomeOdd odd) {
		Wager wager = new WagerBuilder().setOdd(odd).setAmount(io.readWagerAmount())
				.setCurrency(service.findPlayer().getCurrency()).setPlayer(service.findPlayer()).setProcessed(true)
				.setTimestampCreated(LocalDateTime.now()).setWin(findWinners(odd)).build();
		wagerRepository.save(wager);
		return wager;
	}

	private boolean findWinners(OutcomeOdd odd) {
		return "Arsenal".equals(odd.getOutcome().getDescription()) || "1".equals(odd.getOutcome().getDescription())
				? true
				: false;
	}

	private void calculateResults() {
		service.calculateResults();
	}

	private void printResults() {
		io.printResults(service.findPlayer(), service.findAllWagers());
	}

}
