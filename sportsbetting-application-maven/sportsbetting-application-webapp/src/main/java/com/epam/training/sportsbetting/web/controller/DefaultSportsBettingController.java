package com.epam.training.sportsbetting.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.web.service.WebServiceFacade;

@Controller
public class DefaultSportsBettingController {
	@Autowired
	private WebServiceFacade defaultWebService;

	public static final String STATUS_MESSAGE = "STATUS_MESSAGE";

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ModelAndView showEvents(ModelAndView model) {
		defaultWebService.getSportEvents(model);
		return model;
	}

	@RequestMapping(value = "/bets", method = { RequestMethod.GET })
	public String bets() {
		return "bets";
	}

	@RequestMapping(value = "/bets/{id}", method = { RequestMethod.GET })
	public ModelAndView showBets(@PathVariable("id") int id, ModelAndView model) {
		defaultWebService.getBets(id, model);
		return model;
	}
}
