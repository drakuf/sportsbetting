package com.epam.training.sportsbetting.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.training.sportsbetting.web.service.PlayerService;

@Controller
public class PlayerController {
	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showHome(ModelAndView model) {
		playerService.getPlayerData(model);
		return model;
	}
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/saveplayer/{id}", method = RequestMethod.POST)
	public String savePlayer(@RequestParam("name") String name, @RequestParam("birth") String birth,
			@RequestParam("accountNumber") String accountNumber, @RequestParam("currency") String currency,
			@RequestParam("balance") String balance) {
		playerService.updatePlayer(name, birth, accountNumber, currency, balance);
		return "redirect:/home";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		playerService.logoutUser(request, response);
		return "redirect:/";
	}
}
