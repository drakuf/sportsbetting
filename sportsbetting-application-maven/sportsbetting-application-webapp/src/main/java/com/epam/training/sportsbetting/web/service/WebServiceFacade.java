package com.epam.training.sportsbetting.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;

@Component
public class WebServiceFacade {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private OutcomeRepository outcomeRepository;
	@Autowired
	private SportEventRepository sportEventRepository;

	public void getSportEvents(ModelAndView model) {
		List<SportEvent> events = sportEventRepository.findAll();
		model.addObject("event", events);
		model.setViewName("events");
	}

	public void getBets(int id, ModelAndView model) {
		Player player = playerService.getLoggedInUser();
		SportEvent event = sportEventRepository.findById(id).get();
		List<Bet> bets = betRepository.findBetsByEvent(event);
		List<Outcome> outcomes = outcomeRepository.findAll();
		model.addObject("event", event);
		model.addObject("bet", bets);
		model.addObject("outcome", outcomes);
		model.addObject("player", player);
		model.setViewName("bets");
	}
}
