package com.epam.training.sportsbetting.web.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.repository.ResultRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;

@RestController
@RequestMapping("/rest-api/")
public class ResultRestController {

	@Autowired
	private OutcomeRepository outcomeRepository;
	@Autowired
	private ResultRepository resultRepository;
	@Autowired
	private SportEventRepository sportEventRepository;

	@RequestMapping(value = "add-results", method = RequestMethod.POST)
	public ResponseEntity<String> addResults(@RequestBody ResultRest resultRest) {
		List<Outcome> winnerOutcomes = findWinningOutcomes(resultRest.getWinnerOutcomeIds());
		Integer eventId = winnerOutcomes.get(0).getBet().getEvent().getId();
		SportEvent sportEvent = sportEventRepository.findById(eventId).get();
		Result result = new Result();
		result.setWinnerOutcomes(winnerOutcomes);
		sportEvent.setResult(result);
		sportEventRepository.save(sportEvent);
		resultRepository.save(result);
		return new ResponseEntity<String>("Adding new results was successful.", HttpStatus.OK);
	}

	private List<Outcome> findWinningOutcomes(List<Integer> resultREST) {
		List<Outcome> winnerOutcomes = new ArrayList<Outcome>();
		for (Integer outcomeId : resultREST) {
			Outcome outcome = outcomeRepository.findById(outcomeId).get();
			winnerOutcomes.add(outcome);
		}
		return winnerOutcomes;
	}

	@RequestMapping(value = "add-sportevents", method = RequestMethod.POST)
	public ResponseEntity<String> addSportEvents(@RequestBody SportEvent sportEvent) {
		List<Bet> bets = sportEvent.getBets();
		for (Bet bet : bets) {
			bet.setEvent(sportEvent);
			List<Outcome> outcomes = bet.getOutcomes();
			for (Outcome outcome : outcomes) {
				outcome.setBet(bet);
				List<OutcomeOdd> outcomeOdds = outcome.getOutcomeOdds();
				for (OutcomeOdd outcomeOdd : outcomeOdds) {
					outcomeOdd.setOutcome(outcome);
				}
			}
		}
		sportEventRepository.save(sportEvent);
		return new ResponseEntity<String>("Adding new results was successful.", HttpStatus.OK);
	}

}
