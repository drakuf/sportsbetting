package com.epam.training.sportsbetting.web.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.service.AppService;

@Configuration
public class DatabaseTestConfig {
	@Autowired
	private AppService appService;
	@Autowired
	private PlayerRepository playerRepository;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

	@Bean
	public void uploadTestData () {
		appService.uploadSportEvents();
		Player player1 = new Player();
		player1.setAccountNumber(123123123);
		player1.setBalance(new BigDecimal(10000));
		player1.setBirth(LocalDate.of(1988, Month.JANUARY, 11));
		player1.setCurrency(Currency.HUF);
		player1.setName("Petike");
		player1.setEmail("petike@test.com");
		player1.setPassword(passwordEncoder().encode("test"));
		player1.setRole("USER");
		player1.setEnabled(true);
		Player player2 = new Player();
		player2.setAccountNumber(123123321);
		player2.setBalance(new BigDecimal(20000));
		player2.setBirth(LocalDate.of(1981, Month.OCTOBER, 20));
		player2.setCurrency(Currency.HUF);
		player2.setName("Beluska");
		player2.setEmail("beluska@test.com");
		player2.setPassword(passwordEncoder().encode("test"));
		player2.setRole("USER");
		player2.setEnabled(true);
		playerRepository.save(player1);		
		playerRepository.save(player2);		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
