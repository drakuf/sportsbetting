package com.epam.training.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
