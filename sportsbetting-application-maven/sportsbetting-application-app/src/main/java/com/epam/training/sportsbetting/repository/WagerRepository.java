package com.epam.training.sportsbetting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

public interface WagerRepository extends JpaRepository<Wager, Integer> {
	List<Wager> findWagerByPlayer(Player player);
}
