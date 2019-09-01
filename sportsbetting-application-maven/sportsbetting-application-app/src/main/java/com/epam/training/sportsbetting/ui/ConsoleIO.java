package com.epam.training.sportsbetting.ui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.OutcomeOddRepository;

@Component
public class ConsoleIO implements IO {
	private Player player = new Player();
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
	private ConsoleReaderWriter consoleReaderWriter = new ConsoleReaderWriter();
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private OutcomeOddRepository outcomeOddRepository;
	@Value("${default.language}")
	Locale locale;

	@Override
	public Player readPlayerData() {
		String name = askPlayerName();
		BigDecimal balance = askPlayerBalance();
		String currency = askPlayerCurrency();
		setPlayerCurrency(currency);
		player.setName(name);
		player.setBalance(balance);
		return player;
	}

	public String askPlayerName() {
		consoleReaderWriter.printLine(messageSource.getMessage("msg.welcome", null, locale));
		String name = consoleReaderWriter.readLine();
		return name;
	}

	public BigDecimal askPlayerBalance() {
		consoleReaderWriter.printLine(messageSource.getMessage("msg.money", null, locale));
		BigDecimal balance = new BigDecimal(consoleReaderWriter.readLine());
		return balance;
	}

	public String askPlayerCurrency() {
		consoleReaderWriter.printLine(messageSource.getMessage("msg.currency", null, locale));
		String currency = consoleReaderWriter.readLine();
		return currency;
	}

	private void setPlayerCurrency(String currency) {
		if ("HUF".equals(currency)) {
			player.setCurrency(Currency.HUF);
		} else if ("EUR".equals(currency)) {
			player.setCurrency(Currency.EUR);
		} else if ("USD".equals(currency)) {
			player.setCurrency(Currency.USD);
		} else {
			System.err.println("ERROR");
		}
	}

	@Override
	public void printWelcomeMessage(Player player) {
		System.out.println("> Welcome " + player.getName() + "!");
	}

	@Override
	public void printBalance(Player player) {
		System.out.println(
				"> Your balance is " + decimalFormat.format((player.getBalance())) + " " + player.getCurrency());
	}

	@Override
	public OutcomeOdd selectOutcomeOdd(List<SportEvent> sportEvents) {
		printOutcomeOdds(sportEvents);
		String selectOutcome = consoleReaderWriter.readLine();
		OutcomeOdd result = null;
		if (!shouldContinue(selectOutcome)) {
			result = outcomeOddRepository.findAll().get(Integer.parseInt(selectOutcome) - 1);
		}
		return result;
	}

	@Override
	public void printOutcomeOdds(List<SportEvent> sportEvents) {
		System.out.println(messageSource.getMessage("msg.bet", null, locale));
		for (SportEvent sportEvent : sportEvents) {
			printOutcomes(sportEvent);
		}
	}

	private void printOutcomes(SportEvent sportEvent) {
		List<Bet> bets = betRepository.findAll();
		for (int i = 0; i < bets.size(); i++) {
			System.out.println("> " + (i + 1) + ": Sport event: " + sportEvent.getTitle() + " (start: "
					+ dateTimeFormatter.format(sportEvent.getStartDate()) + "), Bet: " + bets.get(i).getDescription()
					+ ", Outcome: " + bets.get(i).getOutcomes().get(0).getDescription() + ", Actual odd: "
					+ bets.get(i).getOutcomes().get(0).getOutcomeOdds().get(0).getValue() + ", Valid between "
					+ dateTimeFormatter.format(bets.get(i).getOutcomes().get(0).getOutcomeOdds().get(0).getValidFrom()) + " and "
					+ dateTimeFormatter.format(bets.get(i).getOutcomes().get(0).getOutcomeOdds().get(0).getValidUntil()) + "."
					);
		}
	}

	private boolean shouldContinue(String outcomeOdd) {
		return "q".equals(outcomeOdd) || "Q".equals(outcomeOdd);
	}

	@Override
	public BigDecimal readWagerAmount() {
		consoleReaderWriter.printLine("> What amount do you wish to bet on it?");
		BigDecimal wagerAmount = new BigDecimal(consoleReaderWriter.readLine());
		return wagerAmount;
	}

	@Override
	public void printWagerSaved(Wager wager) {
		System.out.println("> Wager '" + wager.getOdd().getOutcome().getBet().getDescription() + "="
				+ wager.getOdd().getOutcome().getDescription() + "' of " + wager.getOdd().getOutcome().getDescription()
				+ " [odd: " + wager.getOdd().getValue() + ", amount: " + decimalFormat.format(wager.getAmount())
				+ "] saved!");
	}

	@Override
	public void printNotEnoughBalance(Player player) {
		System.out.println("> You don't have enough money, your balance is " + decimalFormat.format(player.getBalance())
				+ " " + player.getCurrency());
	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		consoleReaderWriter.printLine("> Results:");
		printWagers(wagers);
		consoleReaderWriter.printLine("> Your new balance is " + decimalFormat.format(player.getBalance()) + " "
				+ player.getCurrency() + ".");
	}

	private void printWagers(List<Wager> wagers) {
		wagers.stream().forEach(w -> {
			System.out.println(wagerToString(w));
		});
	}

	private String wagerToString(Wager wager) {
		Outcome outcome = wager.getOdd().getOutcome();
		Bet bet = outcome.getBet();
		return "> Wager '" + bet.getDescription() + "=" + outcome.getDescription() + "' of " + bet.getEvent().getTitle()
				+ " [odd: " + wager.getOdd().getValue() + ", amount: " + decimalFormat.format(wager.getAmount())
				+ "], win: " + wager.isWin();
	}

}
