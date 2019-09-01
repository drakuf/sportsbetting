package com.epam.training.sportsbetting.web.rest.controller;

import java.util.List;

public class ResultRest {
	private List<Integer> winnerOutcomeIds;

	public List<Integer> getWinnerOutcomeIds() {
		return winnerOutcomeIds;
	}

	public void setWinnerOutcomeIds(List<Integer> winnerOutcomeIds) {
		this.winnerOutcomeIds = winnerOutcomeIds;
	}
}
