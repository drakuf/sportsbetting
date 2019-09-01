package com.epam.training.sportsbetting.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Outcome {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "outcome",fetch = FetchType.EAGER)
	private List<OutcomeOdd> outcomeOdds;
	@ManyToOne
	private Bet bet;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OutcomeOdd> getOutcomeOdds() {
		return outcomeOdds;
	}

	public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
		this.outcomeOdds = outcomeOdds;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
