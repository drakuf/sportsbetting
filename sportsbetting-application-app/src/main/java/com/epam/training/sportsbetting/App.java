package com.epam.training.sportsbetting;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.training.sportsbetting.service.AppService;

public class App {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppService.class)) {
			AppService appService = appContext.getBean(AppService.class);
			appService.play();
		}
	}
}
