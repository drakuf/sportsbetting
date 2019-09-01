package com.epam.training.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.SportEvent;

public interface SportEventRepository extends JpaRepository<SportEvent, Integer>{

}
