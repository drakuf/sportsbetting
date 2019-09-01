package com.epam.training.sportsbetting.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.web.service.WagerService;

@Controller
public class WagerController {
	@Autowired
	private WagerService wagerService;

	@RequestMapping(value = "/removewager/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteWager(@PathVariable("id") int id) {
		wagerService.removeWager(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "*/newwager", method = { RequestMethod.GET })
	public String newWager() {
		return "wagers";
	}

	@RequestMapping(value = "*/newwager/{id}", method = { RequestMethod.GET })
	public ModelAndView makeNewWager(@PathVariable("id") int id, ModelAndView model) {
		wagerService.showWagerOptions(id, model);
		return model;
	}

	@RequestMapping(value = "/savewager/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView saveWager(@PathVariable("id") int id, ModelAndView model, @RequestParam("amount") String amount,
			@RequestParam("outcome") String description) {
		wagerService.saveNewWager(model, amount, description);
		return model;
	}

}
