package com.epam.training.sportsbetting.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfigurationDataSource {

	private static String dbUrl = "jdbc:mysql://localhost:3306/sportsbetting_peter_simon?serverTimezone=Europe/Budapest&useSSL=false";
	private static String username = "root";
	private static String password = "root";

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
