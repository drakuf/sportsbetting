package com.epam.training.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.User;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	Player findByEmail(String email);
}
