package com.epam.training.sportsbetting.web.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;

@Component
public class PlayerService {
	@Autowired
	private WagerRepository wagerRepository;
	@Autowired
	private PlayerRepository playerRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Player getLoggedInUser() {
		Player player = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			player = playerRepository.findByEmail(email);
		}
		return player;
	}

	public void updatePlayer(String name, String birth, String accountNumber, String currency, String balance) {
		Player player = getLoggedInUser();
		player.setName(name);
		player.setBirth(LocalDate.parse(birth, formatter));
		player.setCurrency(Currency.valueOf(currency));
		player.setAccountNumber(Integer.parseInt(accountNumber));
		player.setBalance(new BigDecimal(balance));
		player.setVersion(player.getVersion() + 1);
		playerRepository.save(player);
	}

	public void getPlayerData(ModelAndView model) {
		Player player = getLoggedInUser();
		List<Wager> wagers = wagerRepository.findWagerByPlayer(player);
		model.addObject("player", player);
		model.addObject("wagers", wagers);
		model.setViewName("home");
	}
	
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}
}
