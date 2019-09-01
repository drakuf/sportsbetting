package com.epam.training.sportsbetting.config;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SportsBettingLoggerAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(SportsBettingLoggerAspect.class);

	@Before("execution(com.epam.training.sportsbetting.ui* *(..))")
	public void logBeforeMethods(final JoinPoint joinPoint) {
		String targetObjectClassName = joinPoint.getArgs().toString();
		LOGGER.debug(targetObjectClassName + " logged");
	}

	@Around("execution(com.epam.training.sportsbetting.ui* *(..))")
	public Object executionTimeMethods(final ProceedingJoinPoint joinPoint) throws Throwable {
		long startedNanoTime = System.nanoTime();

		LOGGER.warn("METHOD STARTED: " + new Date());

		Object result = joinPoint.proceed();
		LOGGER.warn("METHOD ENDED: " + new Date());
		LOGGER.warn("METHOD LASTED (sec): " + (System.nanoTime() - startedNanoTime) / 1000000000.0);
		return result;
	}

	@AfterReturning(pointcut = "execution(com.epam.training.sportsbetting.ui* *(..))", returning = "returnValue")
	public void logAfterMethods(final JoinPoint joinPoint, Object returnValue) {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg != null) {
				LOGGER.debug(returnValue + " logged");
			}
		}
	}
}
