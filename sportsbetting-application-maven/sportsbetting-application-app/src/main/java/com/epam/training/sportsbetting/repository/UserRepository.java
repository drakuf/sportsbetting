package com.epam.training.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.sportsbetting.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByEmail(String email);
}
