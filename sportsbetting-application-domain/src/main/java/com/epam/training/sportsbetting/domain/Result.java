package com.epam.training.sportsbetting.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Result {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany
	private List<Outcome> winnerOutcomes;

	public List<Outcome> getWinnerOutcomes() {
		return winnerOutcomes;
	}

	public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
		this.winnerOutcomes = winnerOutcomes;
	}

}
