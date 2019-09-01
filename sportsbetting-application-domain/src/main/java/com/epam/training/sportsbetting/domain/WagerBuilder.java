package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WagerBuilder {
	private BigDecimal amount;
	private LocalDateTime timestampCreated;
	private boolean processed;
	private boolean win;
	
	private Player player;
	private OutcomeOdd odd;
	private Currency currency;

	public WagerBuilder setOdd(OutcomeOdd odd) {
		this.odd = odd;
		return this;
	}

	public WagerBuilder setAmount(BigDecimal amount) {
		this.amount = amount;
		return this;
	}

	public WagerBuilder setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}

	public WagerBuilder setPlayer(Player player) {
		this.player = player;
		return this;
	}

	public WagerBuilder setProcessed(boolean processed) {
		this.processed = processed;
		return this;
	}

	public WagerBuilder setTimestampCreated(LocalDateTime timestampCreated) {
		this.timestampCreated = timestampCreated;
		return this;
	}

	public WagerBuilder setWin(boolean win) {
		this.win = win;
		return this;
	}

	public Wager build() {
		return new Wager(amount, timestampCreated, processed, win, player, odd, currency);
	}
}
