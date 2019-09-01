package com.epam.training.sportsbetting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;

@Component
public class DefaultSportsBettingService implements SportsBettingService {

	@Autowired
	private SportEventRepository sportEventRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private WagerRepository wagerRepository;

	@Override
	public List<SportEvent> findAllSportEvents() {
		return sportEventRepository.findAll();
	}

	@Override
	public void saveWager(Wager wager) {
		Player player = findPlayer();
		player.setBalance(findPlayer().getBalance().subtract(wager.getAmount()));
		playerRepository.save(player);
		wagerRepository.save(wager);
	}

	@Override
	public void calculateResults() {
		List<Wager> wagers = findAllWagers();
		Player player = findPlayer();
		wagers.stream().filter(w -> w.isWin()).forEach(wager -> player
				.setBalance(player.getBalance().add(wager.getAmount().multiply(wager.getOdd().getValue()))));
		playerRepository.save(player);
	}

	@Override
	public List<Wager> findAllWagers() {
		return wagerRepository.findAll();
	}

	@Override
	public void savePlayer(Player player) {
		playerRepository.save(player);
	}

	@Override
	public Player findPlayer() {
		return playerRepository.findById(1).get();
	}

}
