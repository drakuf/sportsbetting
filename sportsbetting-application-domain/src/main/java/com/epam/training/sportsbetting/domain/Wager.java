package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wager {
	@Id
	@GeneratedValue
	private int id;
	private BigDecimal amount;
	private LocalDateTime timestampCreated;
	private boolean processed;
	private boolean win;
	@OneToOne
	private Player player;
	@OneToOne(cascade = CascadeType.MERGE)
	private OutcomeOdd odd;
	@Enumerated(EnumType.STRING)
	private Currency currency;

	public Wager() {

	}

	public Wager(BigDecimal amount, LocalDateTime timestampCreated, boolean processed, boolean win, Player player,
			OutcomeOdd odd, Currency currency) {
		this.amount = amount;
		this.timestampCreated = timestampCreated;
		this.processed = processed;
		this.win = win;
		this.player = player;
		this.odd = odd;
		this.currency = currency;
	}

	public int getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestampCreated() {
		return timestampCreated;
	}

	public void setTimestampCreated(LocalDateTime timestampCreated) {
		this.timestampCreated = timestampCreated;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public OutcomeOdd getOdd() {
		return odd;
	}

	public void setOdd(OutcomeOdd odd) {
		this.odd = odd;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
