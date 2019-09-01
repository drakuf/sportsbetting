//package com.epam.training.sportsbetting.ui;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.startsWith;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.PrintStream;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.context.MessageSource;
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
//public class ConsoleIOTest {
//
//	private final String NAME = "Test";
//	private final BigDecimal BALANCE = new BigDecimal(100);
//	private final Currency CURRENCY = Currency.HUF;
//
//	@Mock
//	private MessageSource messageSource;
//	@Mock
//	private ConsoleReaderWriter consoleReaderWriter;
//	@InjectMocks
//	private ConsoleIO underTest;
//
//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testAskPlayerNameShouldReturnPlayerName() {
//		when(consoleReaderWriter.readLine()).thenReturn(NAME);
//		String test = underTest.askPlayerName();
//		assertEquals(NAME, test);
//		//verify(messageSource).getMessage("msg.welcome", null, null);
//	}
//
//	@Test
//	public void testAskPlayerBalanceShouldReturnPlayerBalance() {
//		when(consoleReaderWriter.readLine()).thenReturn("100");
//		BigDecimal test = underTest.askPlayerBalance();
//		assertEquals(new BigDecimal(100), test);
//		//verify(consoleReaderWriter).printLine("> How much money do you have (more than 0)?");
//	}
//
//	@Test
//	public void testAskPlayerCurrencyShouldReturnPlayerCurrency() {
//		when(consoleReaderWriter.readLine()).thenReturn("HUF");
//		String test = underTest.askPlayerCurrency();
//		assertEquals("HUF", test);
//		//verify(consoleReaderWriter).printLine("> What is your currency? (HUF, EUR or USD)");
//	}
//
//	@Test
//	public void testWelcomeMessageShouldPrintWelcomeMessage() {
//		Player player = new Player();
//		player.setName(NAME);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printWelcomeMessage(player);
//		//verify(output).println(startsWith("> Welcome Test!"));
//	}
//
//	@Test
//	public void testPrintBalanceShouldPrintPlayerBalance() {
//		Player player = new Player();
//		player.setBalance(BALANCE);
//		player.setCurrency(CURRENCY);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printBalance(player);
//		//verify(output).println(startsWith("> Your balance is 100 HUF"));
//	}
//
//	@Test
//	public void testPrintOutcomeOddsShouldPrintOutcomeOdds() {
//
//		List<SportEvent> sportEvents = new ArrayList<>();
//		List<Bet> bets = new ArrayList<>();
//		List<Outcome> outcomes = new ArrayList<>();
//		List<OutcomeOdd> outcomeOdds = new ArrayList<>();
//		makeTestOutcomeOdd(sportEvents, bets, outcomes, outcomeOdds);
//
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printOutcomeOdds(sportEvents);
//		//verify(output).println(startsWith("> What are you want to bet on? (choose a number or press q for quit)"));
////		verify(output).println(startsWith(
////				"> 1: Sport event: Arsenal vs Chelsea (start: 2020-01-01 12:00:00), Bet: player Oliver Giroud score, Outcome: 1, Actual odd: 2, Valid between 2000-01-01 12:00:00 and 2020-01-01 12:00:00."));
//	}
//
//	@Test
//	public void testSelectOutComeOddShouldReturnOutcomeOdd() {
//		when(consoleReaderWriter.readLine()).thenReturn("1");
//		List<SportEvent> sportEvents = new ArrayList<>();
//		List<Bet> bets = new ArrayList<>();
//		List<Outcome> outcomes = new ArrayList<>();
//		List<OutcomeOdd> outcomeOdds = new ArrayList<>();
//		OutcomeOdd outcomeOdd = makeTestOutcomeOdd(sportEvents, bets, outcomes, outcomeOdds);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.selectOutcomeOdd(sportEvents);
//		assertEquals(outcomeOdd, underTest.selectOutcomeOdd(sportEvents));
//	}
//
//	@Test
//	public void testReadWagerAmountShouldReturnWagerAmount() {
//		when(consoleReaderWriter.readLine()).thenReturn("100");
//		BigDecimal test = underTest.readWagerAmount();
//		assertEquals(new BigDecimal(100), test);
//		verify(consoleReaderWriter).printLine("> What amount do you wish to bet on it?");
//	}
//
//	@Test
//	public void testPrintWagerSavedShouldPrintWager() {
//		OutcomeOdd odd = new OutcomeOdd();
//		odd.setOutcome(new Outcome());
//		odd.getOutcome().setBet(new Bet());
//		odd.getOutcome().getBet().setDescription("bet description");
//		odd.getOutcome().getBet().setEvent(new SportEvent());
//		odd.getOutcome().getBet().getEvent().setTitle("title");
//
//		Wager wager = new WagerBuilder().setAmount(BALANCE).setCurrency(CURRENCY).setOdd(odd).build();
//
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printWagerSaved(wager);
//		verify(output).println(startsWith("> Wager 'bet description=null' of title [odd: null, amount: 100] saved!"));
//
//	}
//
//	@Test
//	public void testPrintNotEnoughBalanceShouldPrintNotEnoughBalance() {
//		Player player = new Player();
//		player.setBalance(BALANCE);
//		player.setCurrency(CURRENCY);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printNotEnoughBalance(player);
//		verify(output).println(startsWith("> You don't have enough money, your balance is 100 HUF"));
//	}
//
//	@Test
//	public void testPrintResultsShouldPrintResults() {
//		List<Wager> wagers = new ArrayList<Wager>();
//		Player player = new Player();
//		player.setBalance(BALANCE);
//		player.setCurrency(CURRENCY);
//		OutcomeOdd odd = new OutcomeOdd();
//		odd.setOutcome(new Outcome());
//		odd.getOutcome().setBet(new Bet());
//		odd.getOutcome().getBet().setDescription("bet description");
//		odd.getOutcome().getBet().setEvent(new SportEvent());
//		odd.getOutcome().getBet().getEvent().setTitle("title");
//
//		Wager wager = new WagerBuilder().setAmount(BALANCE).setCurrency(CURRENCY).setOdd(odd).build();
//		wagers.add(wager);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		underTest.printResults(player, wagers);
//		verify(consoleReaderWriter).printLine(startsWith("> Results:"));
//		verify(consoleReaderWriter).printLine(startsWith("> Your new balance is 100 HUF."));
//	}
//
//	public OutcomeOdd makeTestOutcomeOdd(List<SportEvent> sportEvents, List<Bet> bets, List<Outcome> outcomes,
//			List<OutcomeOdd> outcomeOdds) {
//		Result result = new Result();
//
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
//		return outcomeOdd;
//	}
//}
