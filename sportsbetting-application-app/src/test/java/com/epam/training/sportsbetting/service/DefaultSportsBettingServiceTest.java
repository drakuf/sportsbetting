//package com.epam.training.sportsbetting.service;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import com.epam.training.sportsbetting.domain.Bet;
//import com.epam.training.sportsbetting.domain.BetType;
//import com.epam.training.sportsbetting.domain.Currency;
//import com.epam.training.sportsbetting.domain.Outcome;
//import com.epam.training.sportsbetting.domain.OutcomeOdd;
//import com.epam.training.sportsbetting.domain.Player;
//import com.epam.training.sportsbetting.domain.Result;
//import com.epam.training.sportsbetting.domain.SportEvent;
//import com.epam.training.sportsbetting.domain.Wager;
//import com.epam.training.sportsbetting.domain.WagerBuilder;
//
//class DefaultSportsBettingServiceTest {
//
//	@Test
//	public void testFindAllSportEventsShouldFindAllSportEvents() {
//		SportEvent sportEvent = makeTestSportEvent();
//		DefaultSportsBettingService sportService = new DefaultSportsBettingService();
//		List<SportEvent> sports = sportService.findAllSportEvents();
//		assertEquals(sports.get(0).getTitle(), sportEvent.getTitle());
//	}
//
//	public SportEvent makeTestSportEvent() {
//		Result result = new Result();
//		List<SportEvent> sportEvents = new ArrayList<>();
//		List<Bet> bets = new ArrayList<>();
//		List<Outcome> outcomes = new ArrayList<>();
//		List<OutcomeOdd> outcomeOdds = new ArrayList<>();
//		SportEvent sportEvent = new SportEvent();
//		sportEvent.setTitle("Arsenal vs Chelsea");
//		sportEvent.setStartDate(LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0));
//		sportEvent.setEndDate(null);
//		sportEvent.setBets(bets);
//		sportEvent.setResult(result);
//
//		Bet bet = new Bet();
//		bet.setType(BetType.WINNER);
//		bet.setDescription("player Oliver Giroud score");
//		bet.setOutcomes(outcomes);
//		bet.setEvent(sportEvent);
//
//		bets.add(bet);
//
//		Outcome outcome = new Outcome();
//		outcome.setDescription("1");
//		outcome.setBet(bet);
//		outcome.setOutcomeOdds(outcomeOdds);
//
//		outcomes.add(outcome);
//
//		OutcomeOdd outcomeOdd = new OutcomeOdd();
//		outcomeOdd.setValue(new BigDecimal("2"));
//		outcomeOdd.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 1, 12, 0));
//		outcomeOdd.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0));
//		outcomeOdd.setOutcome(outcome);
//
//		outcomeOdds.add(outcomeOdd);
//
//		sportEvents.add(sportEvent);
//		return sportEvent;
//	}
//
//	@Test
//	public void testCalculateResultsShouldCalculateResults() {
//		DefaultSportsBettingService sportService = new DefaultSportsBettingService();
//		Player player = new Player();
//		OutcomeOdd odd = new OutcomeOdd();
//		odd.setValue(new BigDecimal(1));
//		player.setBalance(new BigDecimal(100));
//		sportService.savePlayer(player);
//		Wager wager = new WagerBuilder().setAmount(new BigDecimal(100)).setCurrency(Currency.HUF).setOdd(odd)
//				.setWin(true).setPlayer(player).build();
//
//		sportService.saveWager(wager);
//		sportService.calculateResults();
//		assertEquals(wager.getAmount().multiply(wager.getOdd().getValue()), sportService.findPlayer().getBalance());
//	}
//}